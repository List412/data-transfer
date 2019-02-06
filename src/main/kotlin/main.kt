import Model.*
import Tables.*
import com.mysql.jdbc.Connection
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*
import kotlin.collections.HashMap

fun main(args: Array<String>) {
    createTables()

    val rawData = ArrayList<ResultRow>()

    transaction(Connection.TRANSACTION_SERIALIZABLE, 2, Db.db_bad) {
        val query = HeroesTrashCan.selectAll()
        rawData.addAll(query)
    }

    rawData.forEach {
        convert(it)
    }
}

fun createTables() {
    transaction(Db.db_bad) {
//        addLogger(StdOutSqlLogger)
        SchemaUtils.create(HeroesTrashCan)
    }

    transaction(Db.db) {
//        addLogger(StdOutSqlLogger)
        SchemaUtils.drop(Persons, Resumes, SuperPowers, Duties, Jobs, JobsResume)
        SchemaUtils.create(Persons, Resumes, SuperPowers, Duties, Jobs, JobsResume)
    }
}

fun convert(it: ResultRow) {

    println(it[HeroesTrashCan.name])

    val references = HashMap<String, EntityID<Int>>()

    transaction(Db.db) {
        val exist = Person.find { Persons.personId eq it[HeroesTrashCan.personId] }
        if (exist.count() > 0) {
            references["person"] = exist.first().id
            if (it[HeroesTrashCan.age] != null) exist.first().age = it[HeroesTrashCan.age]
        }
        else {
            val person = Person.new {
                personId = it[HeroesTrashCan.personId]
                name = it[HeroesTrashCan.name]
                secondName = it[HeroesTrashCan.secondName]
                middleName = it[HeroesTrashCan.middleName]
                age = it[HeroesTrashCan.age]
            }
            references["person"] = person.id
        }
    }

    if (it[HeroesTrashCan.superPower] != null) {
        transaction(Db.db) {
            val power = SuperPower.new {
                name = it[HeroesTrashCan.superPower]!!
                person = Person.findById(references["person"]!!)
            }
            references["superPower"] = power.id
        }
    }

    if (it[HeroesTrashCan.resume] != null) {
        transaction {
            val personLink = Person.findById(references["person"]!!)
            val resume =Resume.new {
                name = it[HeroesTrashCan.resume]!!
                person = personLink
            }
            references["resume"] = resume.id
        }

        if (it[HeroesTrashCan.workPlace] != null) {
            transaction {
                val resumes = Resume.findById(references["resume"]!!)
                val job = Job.new {
                    name = it[HeroesTrashCan.workPlace]!!
                    startDate = it[HeroesTrashCan.startDate]!!
                    finishDate = it[HeroesTrashCan.finishDate]!!
                }
                resumes!!.jobs = SizedCollection(listOf(job))
                job.resumes = SizedCollection(listOf(resumes))
                references["job"] = job.id
            }

            transaction {
                val resume = Resume.findById(references["resume"]!!)
                val job = Job.findById(references["job"]!!)
                resume!!.jobs = SizedCollection(listOf(job!!))
            }

            if(it[HeroesTrashCan.duty] != null) {
                transaction {
                    val jobReference = Job.findById(references["job"]!!)
                    Duty.new {
                        name = it[HeroesTrashCan.duty]!!
                        job = jobReference!!
                    }
                }
            }
        }
    }

    println("done")
}

fun convert(it: dataset) {

    println(it.name)

    val references = HashMap<String, EntityID<Int>>()

    transaction(Db.db) {
        val exist = Person.find { Persons.personId eq it.personId }
        if (exist.count() > 0) {
            references["person"] = exist.first().id
            if (it.age != null) exist.first().age = it.age
        }
        else {
            val person = Person.new {
                personId = it.personId
                name = it.name
                secondName = it.secondName
                middleName = it.middleName
                age = it.age
            }
            references["person"] = person.id
        }
    }

    if (it.superPower != null) {
        transaction(Db.db) {
            val power = SuperPower.new {
                name = it.superPower!!
                person = Person.findById(references["person"]!!)
            }
            references["superPower"] = power.id
        }
    }

    if (it.resume != null) {
        transaction {
            val personLink = Person.findById(references["person"]!!)
            val exist = Resume.find { Resumes.person eq personLink?.id and (Resumes.name eq it.resume) }
            if (exist.count() > 0) {
                references["resume"] = exist.first().id
            } else {
                val resume = Resume.new {
                    name = it.resume
                    person = personLink
                }
                references["resume"] = resume.id
            }
        }

        if (it.workPlace != null) {
            transaction {
                val resumes = Resume.findById(references["resume"]!!)

                val job = Job.new {
                    name = it.workPlace
                    startDate = it.startDate!!
                    finishDate = it.finishDate!!
                }


                resumes!!.jobs = SizedCollection(listOf(job))
//                job.resumes = SizedCollection(listOf(resumes))
                references["job"] = job.id
            }

            transaction {
                val resume = Resume.findById(references["resume"]!!)
                val job = Job.findById(references["job"]!!)
                resume!!.jobs = SizedCollection(listOf(job!!))
            }

            if(it.duty != null) {
                transaction {
                    val jobReference = Job.findById(references["job"]!!)
                    Duty.new {
                        name = it.duty
                        job = jobReference!!
                    }
                }
            }
        }
    }

    println("done")
}


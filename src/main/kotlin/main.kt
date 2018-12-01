import Tables.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>) {
    transaction(Db.db_bad) {
        addLogger(StdOutSqlLogger)
        SchemaUtils.create(HeroesTrashCan)
    }

    transaction(Db.db) {
        addLogger(StdOutSqlLogger)
        SchemaUtils.create(Persons, Resumes, SuperPowers, Duties, Jobs, JobsResume)
    }


}
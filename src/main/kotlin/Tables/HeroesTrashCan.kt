package Tables

import org.jetbrains.exposed.dao.IntIdTable

object HeroesTrashCan : IntIdTable() {
    val personId = integer("person_id")
    val name = varchar("name", 100)
    val age = integer("age").nullable()
    val secondName = varchar("second_name", 50).nullable()
    val middleName = varchar("middle_name", 50).nullable()
    val resume = varchar("Resumes", 100).nullable()
    val workPlace = varchar("work_place", 100).nullable()
    val startDate = date("start_date").nullable()
    val finishDate = date("finish_date").nullable()
    val duty = varchar("duties", 200).nullable()
    val superPower = varchar("super_power", 200).nullable()
}
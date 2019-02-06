package Tables

import org.jetbrains.exposed.dao.IntIdTable

object Persons: IntIdTable() {
    val personId = integer("personId").index()
    val name = varchar("name", 100).index()
    val secondName = varchar("secondName", 100).nullable()
    val middleName = varchar("middleName", 100).nullable()
    val age = integer("age").nullable()
}
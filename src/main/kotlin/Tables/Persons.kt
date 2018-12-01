package Tables

import org.jetbrains.exposed.dao.IntIdTable

object Persons: IntIdTable() {
    val name = varchar("name", 100).index()
    val secondName = varchar("secondName", 100).index()
    val middleName = varchar("middleName", 100).index()
    val age = integer("age")
}
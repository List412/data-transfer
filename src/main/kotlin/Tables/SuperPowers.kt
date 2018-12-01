package Tables

import org.jetbrains.exposed.dao.IntIdTable

object SuperPowers: IntIdTable() {
    val name = varchar("name", 200)
    val person = reference("person", Tables.Persons).nullable()
}
package Tables

import org.jetbrains.exposed.dao.IntIdTable

object Resumes: IntIdTable() {
    val name = varchar("name", 100)
    val person = reference("person_id", Tables.Persons).nullable()
}
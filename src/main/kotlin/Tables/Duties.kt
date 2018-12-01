package Tables

import org.jetbrains.exposed.dao.IntIdTable

object Duties: IntIdTable() {
    val name = varchar("name", 200)
    val job = reference("job", Tables.Jobs)
}
package Tables

import org.jetbrains.exposed.dao.IntIdTable

object Jobs: IntIdTable() {
    val name = varchar("work_place", 100)
    val startDate = date("start_date")
    val finsihDate = date("finish_date")
}
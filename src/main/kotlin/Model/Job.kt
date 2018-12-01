package Model

import Tables.Jobs
import Tables.Resumes
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

class Job(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Job>(Jobs)

    var name by Jobs.name
    var startDate by Jobs.startDate
    var finishDate by Jobs.finsihDate
    var resume by Resume referencedOn Jobs.resume
}
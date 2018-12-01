package Model

import Tables.JobsResume
import Tables.Resumes
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

class Resume(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Resume>(Resumes)

    var name by Resumes.name
    var person by Person optionalReferencedOn Resumes.person
    var jobs by Job via JobsResume
}
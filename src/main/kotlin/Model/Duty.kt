package Model

import Tables.Duties
import Tables.Persons
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

class Duty(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Duty>(Duties)
    var name by Duties.name
    var job by Person referencedOn Duties.job
}
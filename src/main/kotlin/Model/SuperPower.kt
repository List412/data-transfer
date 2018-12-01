package Model

import Tables.SuperPowers
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

class SuperPower(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<SuperPower>(SuperPowers)

    var name by SuperPowers.name
    var person by Person optionalReferencedOn SuperPowers.person
}
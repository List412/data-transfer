package Model

import Tables.Persons
import Tables.Resumes
import Tables.SuperPowers
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

class Person(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Person>(Persons)

    var name = Persons.name
    var middleName = Persons.middleName
    var lastName = Persons.secondName
    var age = Persons.age

    val superPowers by SuperPower optionalReferencedOn SuperPowers.person
    val resumes by Resume optionalReferencedOn Resumes.person
}
package Model

import Tables.Persons
import Tables.Resumes
import Tables.SuperPowers
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

class Person(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Person>(Persons)

    var personId by Persons.personId
    var name by Persons.name
    var middleName by Persons.middleName
    var secondName by Persons.secondName
    var age by Persons.age

//    val superPowers by SuperPower optionalReferencedOn SuperPowers.person
//    val resumes by Resume optionalReferencedOn Resumes.person
}
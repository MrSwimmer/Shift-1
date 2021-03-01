package com.example.server.db.table

import com.example.server.model.Person
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toPerson() = Person(
    id = this[Persons.id],
    name = this[Persons.name],
    surname = this[Persons.surname],
    age = this[Persons.age],
    occupation = this[Persons.occupation]
)
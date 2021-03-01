package com.example.server.db.table

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object Persons : Table() {
    val id: Column<Long> = Persons.long("id").autoIncrement().primaryKey()
    val name: Column<String> = Persons.text("name")
    val surname: Column<String> = Persons.text("surname")
    val age: Column<Int> = Persons.integer("age")
    val occupation: Column<String?> = Persons.text("occupation").nullable()
}
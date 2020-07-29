package com.example.server.db.table

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object Notes : Table() {
    val id: Column<Long> = Notes.long("id").autoIncrement().primaryKey()
    val title: Column<String> = Notes.text("title")
    val description: Column<String> = Notes.text("description")
}
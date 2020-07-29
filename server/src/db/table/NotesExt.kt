package com.example.server.db.table

import com.example.common.Note
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toNote() = Note(
    id = this[Notes.id],
    title = this[Notes.title],
    description = this[Notes.description]
)
package com.example.server.repository

import com.example.server.db.dbQuery
import com.example.server.db.table.Notes
import com.example.server.db.table.toNote
import org.jetbrains.exposed.sql.selectAll

class NoteRepository {
    suspend fun getAll() =
        dbQuery {
            Notes.selectAll().map { it.toNote() }
        }
}
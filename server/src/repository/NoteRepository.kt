package com.example.server.repository

import com.example.common.CreateNoteDto
import com.example.server.db.dbQuery
import com.example.server.db.table.Notes
import com.example.server.db.table.toNote
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class NoteRepository {
    suspend fun getAll() =
        dbQuery {
            Notes.selectAll().map { it.toNote() }
        }

    suspend fun getPage(start: Long, size: Int) =
        dbQuery {
            Notes.select {
                Notes.id.eq(start)
            }.limit(size).map { it.toNote() }
        }

    suspend fun add(createNoteDto: CreateNoteDto) {
        dbQuery {
            Notes.insert { insertStatement ->
                insertStatement[title] = createNoteDto.title
                insertStatement[description] = createNoteDto.description
            }
        }
    }

    suspend fun delete(id: Long) {
        dbQuery {
            Notes.deleteWhere {
                Notes.id.eq(id)
            }
        }
    }
}
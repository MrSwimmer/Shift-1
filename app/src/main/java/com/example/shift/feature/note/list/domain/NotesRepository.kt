package com.example.shift.feature.note.list.domain

import com.example.common.Note

interface NotesRepository {

    suspend fun getNotes() : List<Note>
}
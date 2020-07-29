package com.example.shift.feature.note.list.domain

import com.example.common.Note

interface NotesRepository {

    fun getNotes() : List<Note>
}
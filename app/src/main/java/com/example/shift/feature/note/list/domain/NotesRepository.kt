package com.example.shift.feature.note.list.domain

import com.example.common.Note

interface NotesRepository {

    suspend fun getNotes(): List<Note>

    suspend fun getPage(start: Long, size: Int): List<Note>
}
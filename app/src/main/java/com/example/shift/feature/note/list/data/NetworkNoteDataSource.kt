package com.example.shift.feature.note.list.data

import com.example.common.Note

interface NetworkNoteDataSource {

    suspend fun getNotes(): List<Note>

    suspend fun getPage(start: Long, size: Int): List<Note>
}

class NetworkNoteDataSourceImpl(private val api: NotesApi) : NetworkNoteDataSource {

    override suspend fun getNotes(): List<Note> =
        api.getAll()

    override suspend fun getPage(start: Long, size: Int) =
        api.getPage(start, size)
}
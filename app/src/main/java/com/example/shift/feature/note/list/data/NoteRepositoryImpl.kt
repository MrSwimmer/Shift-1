package com.example.shift.feature.note.list.data

import com.example.common.Note
import com.example.shift.feature.note.list.domain.NotesRepository

class NoteRepositoryImpl(
    private val networkNoteDataSource: NetworkNoteDataSource
) : NotesRepository {

    override suspend fun getNotes(): List<Note> = networkNoteDataSource.getNotes()

    override suspend fun getPage(start: Long, size: Int): List<Note> =
        networkNoteDataSource.getPage(start, size)
}
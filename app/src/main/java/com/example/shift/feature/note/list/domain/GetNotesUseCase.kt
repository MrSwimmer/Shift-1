package com.example.shift.feature.note.list.domain

import com.example.common.Note


class GetNotesUseCase(
    private val notesRepository: NotesRepository
) {

    suspend operator fun invoke(): List<Note> = notesRepository.getNotes()
}
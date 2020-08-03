package com.example.shift.feature.note.list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Config
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.common.Note
import com.example.shift.feature.note.list.domain.NoteDataSourceFactory
import com.example.shift.feature.note.list.domain.NotesRepository
import com.example.shift.feature.util.viewmodel.SingleLiveEvent

class NoteListViewModel(
    notesRepository: NotesRepository
) : ViewModel() {

    private val notesDataSourceFactory =
        NoteDataSourceFactory(
            notesRepository,
            viewModelScope
        )

    private val config = Config(
        pageSize = 5,
        prefetchDistance = 1,
        enablePlaceholders = false
    )

    val notes: LiveData<PagedList<Note>> = LivePagedListBuilder(notesDataSourceFactory, config).build()

    val noteClickedEvent =
        SingleLiveEvent<Note>()

    fun noteClicked(note: Note) {
        noteClickedEvent(note)
    }
}
package com.example.shift.feature.note.list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Config
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.common.Note
import com.example.shift.feature.note.list.domain.GetNotesUseCase
import com.example.shift.feature.note.list.domain.NoteDataSourceFactory
import com.example.shift.feature.note.list.domain.NotesRepository
import com.example.shift.feature.util.viewmodel.SingleLiveEvent
import kotlinx.coroutines.launch

class NoteListViewModel(
    notesRepository: NotesRepository
) : ViewModel() {

    val noteClickedEvent =
        SingleLiveEvent<Note>()

    private val factory = NoteDataSourceFactory(notesRepository, viewModelScope)
    private val config = Config(
        pageSize = 10,
        prefetchDistance = 1,
        enablePlaceholders = false
    )
    val notes: LiveData<PagedList<Note>> = LivePagedListBuilder(factory, config).build()


    fun noteClicked(note: Note) {
        noteClickedEvent(note)
    }
}
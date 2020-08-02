package com.example.shift.feature.note.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Config
import androidx.paging.LivePagedListBuilder
import com.example.common.Note
import com.example.shift.feature.note.list.data.NoteRepositoryImpl
import com.example.shift.feature.note.list.domain.GetNotesUseCase
import com.example.shift.feature.note.list.domain.NoteDataSourceFactory
import com.example.shift.feature.util.viewmodel.SingleLiveEvent
import kotlinx.coroutines.launch

class NoteListViewModel(
    getNotesUseCase: GetNotesUseCase,
    noteRepository: NoteRepositoryImpl
) : ViewModel() {

    //    val notes = MutableLiveData<List<Note>>()
    val noteClickedEvent =
        SingleLiveEvent<Note>()

    private val notesDataSource = NoteDataSourceFactory(noteRepository, viewModelScope)

    val config = Config(
        pageSize = 100,
        prefetchDistance = 10,
        enablePlaceholders = true
    )

    val notes = LivePagedListBuilder(notesDataSource, config).build()

    init {
        viewModelScope.launch {
            try {
//                notes.value = getNotesUseCase()
            } catch (e: Exception) {
                // show error
            }
        }
    }

    fun noteClicked(note: Note) {
        noteClickedEvent(note)
    }
}
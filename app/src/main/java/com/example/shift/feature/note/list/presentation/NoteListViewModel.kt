package com.example.shift.feature.note.list.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.Note
import com.example.shift.feature.note.list.domain.GetNotesUseCase
import com.example.shift.feature.util.viewmodel.SingleLiveEvent
import kotlinx.coroutines.launch

class NoteListViewModel(
    getNotesUseCase: GetNotesUseCase
) : ViewModel() {

    val notes = MutableLiveData<List<Note>>()
    val noteClickedEvent =
        SingleLiveEvent<Note>()

    init {
        viewModelScope.launch {
            try {
                notes.value = getNotesUseCase()
            } catch (e: Exception) {
                // show error
            }
        }
    }

    fun noteClicked(note: Note) {
        noteClickedEvent(note)
    }
}
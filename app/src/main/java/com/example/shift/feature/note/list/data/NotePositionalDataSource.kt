package com.example.shift.feature.note.list.data

import androidx.paging.PositionalDataSource
import com.example.common.Note
import com.example.shift.feature.note.list.domain.NotesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class NotePositionalDataSource(
    private val repository: NotesRepository,
    private val coroutineScope: CoroutineScope
) : PositionalDataSource<Note>() {

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Note>) {
        val start = params.startPosition
        val size = params.loadSize
        coroutineScope.launch {
            val notes = repository.getPage(start, size)
            callback.onResult(notes)
        }
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Note>) {
        val size = params.pageSize
        coroutineScope.launch {
            val notes = repository.getPage(0, size)
            callback.onResult(notes, 0)
        }
    }
}
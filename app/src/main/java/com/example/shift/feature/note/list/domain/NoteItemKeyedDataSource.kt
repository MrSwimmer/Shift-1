package com.example.shift.feature.note.list.domain

import androidx.paging.ItemKeyedDataSource
import com.example.common.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class NoteItemKeyedDataSource(
    private val repository: NotesRepository,
    private val coroutineScope: CoroutineScope
) : ItemKeyedDataSource<Long, Note>() {
    override fun getKey(item: Note) = item.id

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Note>) {
        coroutineScope.launch {
            val start = params.key
            val size = params.requestedLoadSize
            val notes = repository.getPage(start, size)
            callback.onResult(notes)
        }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Note>) {
    }

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Note>) {
        coroutineScope.launch {
            val size = params.requestedLoadSize / 3
            val notes = repository.getPage(0, size)
            callback.onResult(notes)
        }
    }
}
package com.example.shift.feature.note.list.domain

import androidx.paging.ItemKeyedDataSource
import com.example.common.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class NoteItemKeyedDataSource(
    private val notesRepository: NotesRepository,
    private val coroutineScope: CoroutineScope
) : ItemKeyedDataSource<Long, Note>() {
    override fun getKey(item: Note) = item.id

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Note>) {
        val start = params.key
        val size = params.requestedLoadSize
        coroutineScope.launch {
            notesRepository.getPage(start, size)
        }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Note>) {
    }

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Note>) {
        val size = params.requestedLoadSize
        coroutineScope.launch {
            notesRepository.getPage(0, size)
        }
    }

}
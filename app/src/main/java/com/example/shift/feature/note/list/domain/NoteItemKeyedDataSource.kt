package com.example.shift.feature.note.list.domain

import androidx.paging.ItemKeyedDataSource
import com.example.common.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class NoteItemKeyedDataSource(
    private val notesRepository: NotesRepository,
    private val coroutineScope: CoroutineScope
) : ItemKeyedDataSource<Int, Note>() {
    override fun getKey(item: Note) = item.id.toInt()

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Note>) {
        val start = params.key
        val size = params.requestedLoadSize
        coroutineScope.launch {
            notesRepository.getPage(start, size)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Note>) {
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Note>) {
        val size = params.requestedLoadSize
        coroutineScope.launch {
            notesRepository.getPage(0, size)
        }
    }

}
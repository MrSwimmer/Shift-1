package com.example.shift.feature.note.list.domain

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.common.Note
import kotlinx.coroutines.CoroutineScope

class NoteDataSourceFactory(
    private val notesRepository: NotesRepository,
    private val coroutineScope: CoroutineScope
) : DataSource.Factory<Int, Note>() {

    private val liveDataSource = MutableLiveData<NoteItemKeyedDataSource>()

    override fun create(): DataSource<Int, Note> {
        val source =
            NoteItemKeyedDataSource(
                notesRepository,
                coroutineScope
            )
        liveDataSource.postValue(source)
        return source
    }
}
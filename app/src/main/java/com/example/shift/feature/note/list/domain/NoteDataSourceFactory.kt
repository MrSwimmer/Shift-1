package com.example.shift.feature.note.list.domain

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.common.Note
import kotlinx.coroutines.CoroutineScope

class NoteDataSourceFactory(
    private val repository: NotesRepository,
    private val coroutineScope: CoroutineScope
) : DataSource.Factory<Long, Note>() {

    private val liveDataSource = MutableLiveData<NoteItemKeyedDataSource>()

    override fun create(): DataSource<Long, Note> {
        val source =
            NoteItemKeyedDataSource(
                repository,
                coroutineScope
            )
        liveDataSource.postValue(source)
        return source
    }
}
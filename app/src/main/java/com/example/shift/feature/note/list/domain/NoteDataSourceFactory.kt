package com.example.shift.feature.note.list.domain

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.common.Note
import com.example.shift.feature.note.list.data.NotePositionalDataSource
import kotlinx.coroutines.CoroutineScope

class NoteDataSourceFactory(
    private val repository: NotesRepository,
    private val coroutineScope: CoroutineScope
) : DataSource.Factory<Int, Note>() {

    val liveDataSource = MutableLiveData<NotePositionalDataSource>()

    override fun create(): DataSource<Int, Note> {
        val source =
            NotePositionalDataSource(
                repository,
                coroutineScope
            )
        liveDataSource.postValue(source)
        return source
    }
}
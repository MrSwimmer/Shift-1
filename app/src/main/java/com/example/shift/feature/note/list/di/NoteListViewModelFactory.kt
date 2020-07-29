package com.example.shift.feature.note.list.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shift.feature.note.list.data.NetworkNoteDataSourceImpl
import com.example.shift.feature.note.list.data.NoteRepositoryImpl
import com.example.shift.feature.note.list.data.NotesApi
import com.example.shift.feature.note.list.domain.GetNotesUseCase
import com.example.shift.feature.note.list.presentation.NoteListViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NoteListViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass == NoteListViewModel::class.java) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://shift-stream-back.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            val api = retrofit.create(NotesApi::class.java)

            val networkDataSource = NetworkNoteDataSourceImpl(api)
            val noteRepository = NoteRepositoryImpl(networkDataSource)
            val getNoteUseCase = GetNotesUseCase(noteRepository)

            return NoteListViewModel(getNoteUseCase) as T
        } else {
            error("Unexpected class $modelClass")
        }
    }
}
package com.example.shift.feature.note.list.data

import com.example.common.Note
import retrofit2.http.GET

interface NotesApi {
    @GET("/notes")
    suspend fun getAll(): List<Note>
}
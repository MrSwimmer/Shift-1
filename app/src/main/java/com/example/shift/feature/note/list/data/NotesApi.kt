package com.example.shift.feature.note.list.data

import com.example.common.Note
import retrofit2.http.GET
import retrofit2.http.Query

interface NotesApi {
    @GET("/notes")
    suspend fun getAll(): List<Note>

    @GET("/notes")
    suspend fun getPage(@Query("start") start: Int, @Query("size") size: Int): List<Note>
}
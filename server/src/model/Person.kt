package com.example.server.model

data class Person(
    val id: Long,
    val name: String,
    val surname: String,
    val age: Int,
    val occupation: String? = null
)
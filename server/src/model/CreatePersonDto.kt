package com.example.server.model

import java.io.Serializable

data class CreatePersonDto(
    val name: String,
    val surname: String,
    val age: Int,
    val occupation: String? = null
) : Serializable
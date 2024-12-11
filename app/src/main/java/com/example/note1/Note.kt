package com.example.note1

data class Note(
    val id: Int,
    val text: String,
    val dateTime: String,
    var isCompleted: Boolean = false
)
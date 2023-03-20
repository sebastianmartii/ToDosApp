package com.example.todos.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos_table")
data class ToDos(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val description: String,
    val completed: Boolean = false
)
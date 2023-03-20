package com.example.todos.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {
    @Insert
    suspend fun addToDo(toDo: ToDos)

    @Delete
    suspend fun delete(toDo: ToDos)

    @Delete
    suspend fun deleteAll(list: List<ToDos>)

    @Update
    suspend fun update(toDo: ToDos)

    @Query("SELECT * FROM todos_table")
    fun getAllToDos(): Flow<List<ToDos>>

    @Query("SELECT * FROM todos_table WHERE id = :toDoId")
    suspend fun getToDo(toDoId: Int): ToDos
}
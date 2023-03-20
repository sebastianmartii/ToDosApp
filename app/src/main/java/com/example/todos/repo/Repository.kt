package com.example.todos.repo

import android.content.Context
import com.example.todos.database.ToDoDao
import com.example.todos.database.ToDoDb
import com.example.todos.database.ToDos
import kotlinx.coroutines.flow.Flow

class Repository(context: Context) : ToDoDao {

    private val toDoDao = ToDoDb.getInstance(context).ToDoDao()

    override suspend fun addToDo(toDo: ToDos) {
        toDoDao.addToDo(toDo)
    }

    override suspend fun delete(toDo: ToDos) {
        toDoDao.delete(toDo)
    }

    override suspend fun deleteAll(list: List<ToDos>) {
        toDoDao.deleteAll(list)
    }

    override suspend fun update(toDo: ToDos) {
        toDoDao.update(toDo)
    }

    override fun getAllToDos(): Flow<List<ToDos>> {
        return toDoDao.getAllToDos()
    }

    override suspend fun getToDo(toDoId: Int): ToDos {
        return toDoDao.getToDo(toDoId)
    }
}
package com.example.todos.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todos.database.ToDos
import com.example.todos.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = Repository(application)
    val toDos = repo.getAllToDos()
    var toDo by mutableStateOf(ToDos(id = 0, title = "", description = ""))


    var openDeleteDialog by mutableStateOf(false)
    var openMarkAsCompletedDialog by mutableStateOf(false)
    var showCompletedToDos by mutableStateOf(false)

    fun getToDo(toDoId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            toDo = repo.getToDo(toDoId)
        }
    }

    fun deleteList(toDoList: List<ToDos>) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteAll(toDoList)
        }
    }

    fun updateToDo(toDo: ToDos) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.update(toDo)
        }
    }

    fun changeToDoStatus() {
        toDo = toDo.copy(
            completed = !toDo.completed
        )
    }

    fun openDeleteDialog() {
        openDeleteDialog = true
    }

    fun openMarkAsCompletedDialog() {
        openMarkAsCompletedDialog = true
    }

    fun closeDeleteDialog() {
        openDeleteDialog = false
    }

    fun closeMarkAsCompletedDialog() {
        openMarkAsCompletedDialog = false
    }

    fun showCompletedToDos() {
        showCompletedToDos = true
    }

    fun showUncompletedToDos() {
        showCompletedToDos = false
    }

}
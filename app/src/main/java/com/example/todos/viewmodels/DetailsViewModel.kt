package com.example.todos.viewmodels

import android.app.Application
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todos.database.ToDos
import com.example.todos.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = Repository(application)
    var toDo by mutableStateOf(ToDos(id = 0, title = "", description = ""))

    var openDialog by mutableStateOf(false)

    fun getToDo(toDoId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            toDo = repo.getToDo(toDoId)
        }
    }

    fun addToDo(toDo: ToDos) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.addToDo(toDo)
        }
    }

    fun deleteToDo(toDo: ToDos) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.delete(toDo)
        }
    }

    fun updateToDo(toDo: ToDos) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.update(toDo)
        }
    }

    fun updateToDoTitle(title: String) {
        toDo = toDo.copy(
            title = title
        )
    }

    fun updateToDoDescription(description: String) {
        toDo = toDo.copy(
            description = description
        )
    }

    fun openDialog() {
        openDialog = true
    }

    fun closeDialog() {
        openDialog = false
    }
}
package com.example.todos.ui.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todos.database.ToDos

@Composable
fun ToDoListContent(
    toDoList: List<ToDos>,
    onItemClick: (Int) -> Unit,
    paddingValues: PaddingValues,
    markItemAsCompleted: (ToDos) -> Unit
) {
    if (toDoList.isEmpty()) {
        EmptyToDoList(paddingValues)
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = paddingValues
        ) {
            items(items = toDoList) { toDo ->
                ToDoItem(
                    toDoItem = toDo,
                    onItemEdit = onItemClick,
                    onMarkItemAsCompleted = markItemAsCompleted,
                    modifier = Modifier
                        .padding(2.dp)
                )
            }
        }
    }
}
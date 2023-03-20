package com.example.todos.ui.home

import android.content.res.Configuration
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todos.ui.dialogs.DeleteDialog
import com.example.todos.ui.dialogs.MarkAsCompleteDialog
import com.example.todos.ui.theme.ToDosTheme
import com.example.todos.viewmodels.ListViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun ToDoListScreen(
    onItemClick: (Int) -> Unit,
    onFabClicked: () -> Unit,
    listViewModel: ListViewModel = viewModel()
) {
    val toDos by listViewModel.toDos.collectAsState(initial = emptyList())
    val uncompletedList = toDos.filter { !it.completed }
    val completedList = toDos.filter { it.completed }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = { listViewModel.showUncompletedToDos() }) {
                        Icon(
                            imageVector = Icons.Outlined.Home,
                            contentDescription = "Uncompleted TODOs"
                        )
                    }
                    IconButton(onClick = { listViewModel.showCompletedToDos() }) {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "Completed TODOs"
                        )
                    }
                    IconButton(
                        onClick = { listViewModel.openDeleteDialog() }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = "Delete",
                        )
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            onFabClicked()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Add a TODO",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { values ->
        AnimatedContent(targetState = listViewModel.showCompletedToDos) {
            when (it) {
                false -> ToDoListContent(
                    toDoList = uncompletedList,
                    onItemClick = onItemClick,
                    paddingValues = values,
                    markItemAsCompleted = { toDo ->
                        listViewModel.getToDo(toDo.id)
                        listViewModel.openMarkAsCompletedDialog()
                    }
                )
                else -> ToDoListContent(
                    toDoList = completedList,
                    onItemClick = onItemClick,
                    paddingValues = values,
                    markItemAsCompleted = {toDo ->
                        listViewModel.getToDo(toDo.id)
                        listViewModel.openMarkAsCompletedDialog()
                    }
                )
            }
        }

        DeleteDialog(
            openDialog = listViewModel.openDeleteDialog,
            closeDialog = { listViewModel.closeDeleteDialog() },
            delete = { listViewModel.deleteList(toDoList = if (listViewModel.showCompletedToDos) completedList else uncompletedList) }
        )
        MarkAsCompleteDialog(
            status = if (listViewModel.toDo.completed) "uncompleted" else "completed",
            openDialog = listViewModel.openMarkAsCompletedDialog,
            closeDialog = { listViewModel.closeMarkAsCompletedDialog() },
            markAsCompleted = {
                listViewModel.changeToDoStatus()
                listViewModel.updateToDo(listViewModel.toDo)
            }
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ToDoListScreenPreview() {
    ToDosTheme {
        ToDoListScreen(onItemClick = {}, onFabClicked = {})
    }
}
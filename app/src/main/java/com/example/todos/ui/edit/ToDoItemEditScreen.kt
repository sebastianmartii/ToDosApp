package com.example.todos.ui.edit

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todos.ui.details.ToDoItemEdit
import com.example.todos.ui.dialogs.DeleteDialog
import com.example.todos.ui.theme.ToDosTheme
import com.example.todos.viewmodels.DetailsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoItemEditScreen(
    toDoId: Int,
    onNavigateBack: () -> Unit,
    detailsViewModel: DetailsViewModel = viewModel()
) {

    LaunchedEffect(Unit) {
        detailsViewModel.getToDo(toDoId)
    }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = { onNavigateBack() }) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = "Navigate Back"
                        )
                    }
                    IconButton(onClick = {
                        detailsViewModel.openDialog()
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = "Delete ToDo"
                        )
                    }
                },
                floatingActionButton = {
                    ExtendedFloatingActionButton(
                        text = {
                            Text(text = "Save")
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = "Save TODO"
                            )
                        },
                        onClick = {
                            detailsViewModel.updateToDo(detailsViewModel.toDo)
                                .also { onNavigateBack() }
                        }
                    )
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { values ->
        ToDoItemEdit(
            modifier = Modifier.padding(values),
            toDo = detailsViewModel.toDo,
            updateTitle = { title ->
                detailsViewModel.updateToDoTitle(title)
            },
            updateDescription = { description ->
                detailsViewModel.updateToDoDescription(description)
            }
        )
        DeleteDialog(
            openDialog = detailsViewModel.openDialog,
            closeDialog = { detailsViewModel.closeDialog() },
            delete = {
                detailsViewModel.deleteToDo(detailsViewModel.toDo)
                onNavigateBack()
            }
        )
    }
}


@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ToDoItemEditScreenPreview() {
    ToDosTheme {
        ToDoItemEditScreen(toDoId = 0, onNavigateBack = {})
    }
}
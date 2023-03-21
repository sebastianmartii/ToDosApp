package com.example.todos.ui.add

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todos.ui.details.ToDoItemEdit
import com.example.todos.ui.theme.ToDosTheme
import com.example.todos.viewmodels.DetailsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoItemAddScreen(
    onNavigateBack: () -> Unit,
    detailsViewModel: DetailsViewModel = viewModel()
) {
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
                    },
                    floatingActionButton = {
                        ExtendedFloatingActionButton(
                            text = {
                                Text(text = "Add")
                            },
                            icon = {
                                Icon(
                                    imageVector = Icons.Filled.Done,
                                    contentDescription = "Save TODO"
                                )
                            },
                            onClick = { detailsViewModel.addToDo(detailsViewModel.toDo. also { onNavigateBack() }) }
                        )
                    }
                )
            },
            modifier = Modifier.fillMaxSize()) { values ->
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
        }
    }



@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ToDoItemEditScreenPreview() {
    ToDosTheme {
        ToDoItemAddScreen(onNavigateBack = {})
    }
}
package com.example.todos.ui.details

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todos.database.ToDos
import com.example.todos.ui.theme.ToDosTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoItemEdit(
    modifier: Modifier = Modifier,
    toDo: ToDos,
    updateTitle: (String) -> Unit,
    updateDescription: (String) -> Unit
) {
    Column(modifier = modifier
        .fillMaxSize()
        .padding(horizontal = 4.dp)
    ) {
            OutlinedTextField(
                value = toDo.title,
                onValueChange = { title ->
                    updateTitle(title)
                },
                label = {
                    Text(
                        text = "Title",
                    )
                },
                shape = MaterialTheme.shapes.small,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .padding(2.dp)
            )
            OutlinedTextField(
                value = toDo.description,
                onValueChange = { description ->
                    updateDescription(description)
                },
                label = {
                    Text(
                        text = "Description",
                    )
                },
                modifier = Modifier
                    .fillMaxSize()
                    .weight(9f)
                    .padding(2.dp)
            )
        }

    }



@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun ToDoItemEditPreview() {
    val toDo = ToDos(0, "", "")
    ToDosTheme {
        ToDoItemEdit(toDo = toDo, updateTitle = {}, updateDescription = {})
    }
}
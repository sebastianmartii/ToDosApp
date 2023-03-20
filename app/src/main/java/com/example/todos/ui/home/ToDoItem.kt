package com.example.todos.ui.home

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todos.database.ToDos
import com.example.todos.ui.theme.ToDosTheme

@Composable
fun ToDoItem(
    toDoItem: ToDos,
    onItemEdit: (Int) -> Unit,
    onMarkItemAsCompleted: (ToDos) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Card(
        shape = MaterialTheme.shapes.small,
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                if (toDoItem.description.length > 1) expanded = !expanded
            }
        ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier.padding(
                    horizontal = 8.dp,
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = toDoItem.title,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp)
                )
                IconButton(onClick = { onMarkItemAsCompleted(toDoItem) }) {
                    Icon(
                        imageVector = if (!toDoItem.completed) Icons.Filled.Done else Icons.Filled.Close,
                        contentDescription = "Completed"
                    )
                }
                IconButton(onClick = { onItemEdit(toDoItem.id) }) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "Edit"
                    )
                }
            }
            AnimatedVisibility(expanded){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Divider(
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                    )
                    Text(
                        text = toDoItem.description,
                        color = MaterialTheme.colorScheme.onSurface.copy(0.5f),
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(
                            start = 24.dp,
                            end = 24.dp,
                            top = 8.dp,
                            bottom = 8.dp
                        )
                    )
                }
            }
        }
       }
    }


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ToDoItemPreview() {
    val todo = ToDos(1,"some random todo title", "description description description description description description description description description")
    ToDosTheme {
        ToDoItem(toDoItem = todo, modifier = Modifier, onItemEdit = {}, onMarkItemAsCompleted = {})
    }
}
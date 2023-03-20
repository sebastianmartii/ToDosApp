package com.example.todos.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration

@Composable
fun EmptyToDoList(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Text(
            text = "You have completed all Your TODOs",
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "Press the plus button to add some",
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f),
            textDecoration = TextDecoration.Underline,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

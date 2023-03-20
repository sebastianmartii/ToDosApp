package com.example.todos.ui.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun MarkAsCompleteDialog(
    status: String,
    openDialog: Boolean,
    closeDialog: () -> Unit,
    markAsCompleted: () -> Unit
) {
    if (openDialog) {
        AlertDialog(
            onDismissRequest = closeDialog,
            text = {
                Text(text = "Are you sure you want to mark this TODO as $status?")
            },
            confirmButton = {
                TextButton(onClick = {
                    closeDialog()
                    markAsCompleted()
                }) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = closeDialog) {
                    Text(text = "Dismiss")
                }
            }
        )
    }
}
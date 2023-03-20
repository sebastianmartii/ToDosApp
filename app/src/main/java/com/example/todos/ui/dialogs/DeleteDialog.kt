package com.example.todos.ui.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun DeleteDialog(
    openDialog: Boolean,
    closeDialog: () -> Unit,
    delete: () -> Unit,
) {
    if (openDialog){
        AlertDialog(
            onDismissRequest = closeDialog,
            text = {
                Text(text = "Upon deleting there is no way to restore any of the deleted data")
            },
            confirmButton = {
                TextButton(onClick = {
                    closeDialog()
                    delete()
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
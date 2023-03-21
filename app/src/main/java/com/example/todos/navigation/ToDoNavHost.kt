package com.example.todos.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todos.ui.add.ToDoItemAddScreen
import com.example.todos.ui.edit.ToDoItemEditScreen
import com.example.todos.ui.home.ToDoListScreen

@Composable
fun ToDoNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = List.route,
        modifier = modifier
    ) {
        composable(route = List.route) {
            ToDoListScreen(
                onItemClick = {toDoId ->
                    navController.navigate(route = "${Edit.route}/$toDoId")
                },
                onFabClicked = {
                    navController.navigate(route = Add.route)
                }
            )
        }
        composable(route = Add.route) {
            ToDoItemAddScreen(onNavigateBack = {
                navController.popBackStack()
            })
        }
        composable(
            route = Edit.routeWithArgs,
            arguments = Edit.arguments
        ) { backStackEntry ->
            ToDoItemEditScreen(
                backStackEntry.arguments?.getInt(Edit.idArgs) ?: 0,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
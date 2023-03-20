package com.example.todos

import androidx.navigation.NavType
import androidx.navigation.navArgument


interface ToDoDestinations {
    val route: String
}

object List : ToDoDestinations {
    override val route = "list"
}

object Add : ToDoDestinations {
    override val route = "add"
}

object Edit : ToDoDestinations {
    override val route = "edit"
    const val idArgs = "id"
    val routeWithArgs = "$route/{$idArgs}"
    val arguments = listOf(
        navArgument(idArgs) {
            type = NavType.IntType
        }
    )

}

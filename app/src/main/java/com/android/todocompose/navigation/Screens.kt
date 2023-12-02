package com.android.todocompose.navigation

import androidx.navigation.NavHostController
import com.android.todocompose.util.Action
import com.android.todocompose.util.Constants.LIST_SCREEN
import com.android.todocompose.util.Constants.SPLASH_SCREEN
import com.android.todocompose.util.Constants.TASK_SCREEN

class Screens(navController: NavHostController) {

    val splash: () -> Unit = { ->
        navController.navigate(route = "list/${Action.NO_ACTION.name}") {
            popUpTo(SPLASH_SCREEN) {
                inclusive = true
            }
        }
    }

    val list: (Int) -> Unit = { taskId ->
        navController.navigate(route = "task/$taskId")
    }

    val task: (Action) -> Unit = { action ->
        navController.navigate(route = "list/${action.name}") {
            popUpTo(LIST_SCREEN) {
                inclusive = true
            }
        }
    }

}
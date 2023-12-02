package com.android.todocompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.android.todocompose.navigation.destinations.listComposable
import com.android.todocompose.navigation.destinations.splashComposable
import com.android.todocompose.navigation.destinations.taskComposable
import com.android.todocompose.ui.viewmodels.SharedViewModel
import com.android.todocompose.util.Constants.SPLASH_SCREEN

@Composable
fun AppNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {

    val screen = remember(navController) {
        Screens(navController = navController)
    }

    NavHost(
        navController = navController,
        startDestination = SPLASH_SCREEN
    ) {
        splashComposable(
            navigateToListScreen = screen.splash
        )
        listComposable(
            navigateToTaskScreen = screen.list,
            sharedViewModel = sharedViewModel
        )
        taskComposable(
            navigateToListScreen = screen.task,
            sharedViewModel = sharedViewModel
        )
    }

}
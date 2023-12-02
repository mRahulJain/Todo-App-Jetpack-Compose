package com.android.todocompose.ui.screens.task

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.android.todocompose.data.models.Priority
import com.android.todocompose.data.models.TodoTask
import com.android.todocompose.ui.viewmodels.SharedViewModel
import com.android.todocompose.util.Action

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TaskScreen(
    navigateToListScreen: (Action) -> Unit,
    sharedViewModel: SharedViewModel,
    selectedTask: TodoTask?
) {

    val title: String by sharedViewModel.title
    val description: String by sharedViewModel.description
    val priority: Priority by sharedViewModel.priority

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TaskAppBar(
                navigateToListScreen = {
                    if (it == Action.NO_ACTION) {
                        navigateToListScreen(it)
                    } else {
                        if (sharedViewModel.validateFields()) {
                            navigateToListScreen(it)
                        } else {
                            Toast.makeText(context, "Fields Empty", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                selectedTask = selectedTask)
        },
        content = {
            TaskContent(
                title = title,
                onTitleChange = {
                    sharedViewModel.updateTitle(it)
                },
                description = description,
                onDescriptionChange = {
                    sharedViewModel.description.value = it
                },
                priority = priority,
                onPrioritySelected = {
                    sharedViewModel.priority.value = it
                }
            )
        }
    )
}
package com.android.todocompose.ui.screens.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.todocompose.R
import com.android.todocompose.components.DisplayAlertDialog
import com.android.todocompose.data.models.Priority
import com.android.todocompose.data.models.TodoTask
import com.android.todocompose.ui.screens.list.ListAppBarActions
import com.android.todocompose.ui.theme.*
import com.android.todocompose.util.Action
import com.android.todocompose.util.TrailingIconState

@Composable
fun TaskAppBar(
    navigateToListScreen: (Action) -> Unit,
    selectedTask: TodoTask?
) {
    if (selectedTask == null) {
        NewTaskAppBar(
            navigateToListScreen = navigateToListScreen
        )
    } else {
        ExistingTaskAppBar(
            todoTask = selectedTask,
            navigateToListScreen = navigateToListScreen
        )
    }
}

@Composable
fun NewTaskAppBar(
    navigateToListScreen: (Action) -> Unit
) {
    TopAppBar(
        navigationIcon = {
            BackAction(onBackClicked = navigateToListScreen)
        },
        title = {
            Text(
                text = stringResource(id = R.string.new_task),
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        actions = {
            SaveAction(onSaveClicked = navigateToListScreen)
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor
    )
}

@Composable
fun BackAction(
    onBackClicked: (Action) -> Unit
) {
    IconButton(
        modifier = Modifier
            .padding(MEDIUM_PADDING),
        onClick = { onBackClicked(Action.NO_ACTION) }
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(id = R.string.arrow_back),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun SaveAction(
    onSaveClicked: (Action) -> Unit
) {
    IconButton(
        modifier = Modifier
            .padding(MEDIUM_PADDING),
        onClick = { onSaveClicked(Action.ADD) }
    ) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(id = R.string.task_save),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun ExistingTaskAppBar(
    todoTask: TodoTask,
    navigateToListScreen: (Action) -> Unit
) {
    TopAppBar(
        navigationIcon = {
            CloseAction(onCloseClicked = navigateToListScreen)
        },
        title = {
            Text(
                text = todoTask.title,
                color = MaterialTheme.colors.topAppBarContentColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        actions = {
            ExistingTaskAppBarActions(
                todoTask = todoTask,
                navigateToListScreen = navigateToListScreen
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor
    )
}

@Composable
fun ExistingTaskAppBarActions(
    todoTask: TodoTask,
    navigateToListScreen: (Action) -> Unit
) {

    var openDialog by remember {
        mutableStateOf(false)
    }
    DisplayAlertDialog(
        title = stringResource(id = R.string.delete_task_title, todoTask.title),
        message = stringResource(id = R.string.delete_task_message, todoTask.title),
        openDialog = openDialog,
        closeDialog = { openDialog = false },
        onYesClicked = { navigateToListScreen(Action.DELETE) }
    )
    DeleteAction(onDeleteClicked = {
        openDialog = true
    })

    UpdateAction(onUpdateClicked = navigateToListScreen)
}

@Composable
fun CloseAction(
    onCloseClicked: (Action) -> Unit
) {
    IconButton(
        modifier = Modifier
            .padding(MEDIUM_PADDING),
        onClick = { onCloseClicked(Action.NO_ACTION) }
    ) {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = stringResource(id = R.string.close_task),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun DeleteAction(
    onDeleteClicked: () -> Unit
) {
    IconButton(
        modifier = Modifier
            .padding(MEDIUM_PADDING),
        onClick = { onDeleteClicked() }
    ) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.delete_task),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun UpdateAction(
    onUpdateClicked: (Action) -> Unit
) {
    IconButton(
        modifier = Modifier
            .padding(MEDIUM_PADDING),
        onClick = { onUpdateClicked(Action.UPDATE) }
    ) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(id = R.string.update_task),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
@Preview
fun NewTaskAppBarPreview() {
    NewTaskAppBar {}
}

@Composable
@Preview
fun ExistingTaskAppBarPreview() {
    ExistingTaskAppBar(
        todoTask = TodoTask(0, "Sample Task", "Sample Description", Priority.LOW),
        navigateToListScreen = {}
    )
}
package com.android.todocompose.data.repositories

import com.android.todocompose.data.TodoDAO
import com.android.todocompose.data.models.TodoTask
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class TodoRepository @Inject constructor(
    private val todoDAO: TodoDAO
) {

    val getAllTasks: Flow<List<TodoTask>> = todoDAO.getAllTasks()
    val sortByLowPriority: Flow<List<TodoTask>> = todoDAO.sortByLowPriority()
    val sortByHighPriority: Flow<List<TodoTask>> = todoDAO.sortByHighPriority()

    fun getSelectedTask(taskId: Int) : Flow<TodoTask> {
        return todoDAO.getSelectedTask(taskId)
    }

    suspend fun addTask(todoTask: TodoTask) {
        todoDAO.addTask(todoTask)
    }

    suspend fun updateTask(todoTask: TodoTask) {
        todoDAO.updateTask(todoTask)
    }

    suspend fun deleteTask(todoTask: TodoTask) {
        todoDAO.deleteTask(todoTask)
    }

    suspend fun deleteAllTasks() {
        todoDAO.deleteAllTasks()
    }

    fun searchDatabase(searchQuery: String) : Flow<List<TodoTask>> {
        return todoDAO.searchDatabase(searchQuery)
    }

}
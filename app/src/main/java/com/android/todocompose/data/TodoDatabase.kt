package com.android.todocompose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.todocompose.data.models.TodoTask

@Database(
    entities = [TodoTask::class],
    version = 1,
    exportSchema = false
)
abstract class TodoDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDAO
}
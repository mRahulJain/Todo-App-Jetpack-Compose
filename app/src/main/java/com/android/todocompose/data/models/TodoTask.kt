package com.android.todocompose.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.todocompose.util.Constants

@Entity(
    tableName = Constants.DATABASE_TABLE
)
data class TodoTask(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val priority: Priority
)

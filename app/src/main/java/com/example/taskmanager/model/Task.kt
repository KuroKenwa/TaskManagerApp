package com.example.taskmanager.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val status: TaskStatus = TaskStatus.PENDING,
    val priority: Priority = Priority.MEDIUM
)
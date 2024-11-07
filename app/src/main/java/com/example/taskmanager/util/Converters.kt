package com.example.taskmanager.util

import androidx.room.TypeConverter
import com.example.taskmanager.model.TaskStatus

class Converters {
    @TypeConverter
    fun fromTaskStatus(status: TaskStatus): String {
        return status.name
    }

    @TypeConverter
    fun toTaskStatus(value: String): TaskStatus {
        return TaskStatus.valueOf(value)
    }
}
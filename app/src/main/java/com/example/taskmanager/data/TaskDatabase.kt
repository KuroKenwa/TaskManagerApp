package com.example.taskmanager.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.taskmanager.model.Task
import com.example.taskmanager.util.Converters

@Database(
    entities = [Task::class],
    version = 1,
    exportSchema = false  // מונע את השגיאה של schema export
)
@TypeConverters(Converters::class)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
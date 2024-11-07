package com.example.taskmanager.data

import androidx.room.*
import com.example.taskmanager.model.Task
import com.example.taskmanager.model.TaskStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    fun getAllTasks(): Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * FROM tasks WHERE id = :taskId")
    suspend fun getTaskById(taskId: Int): Task?

    // שינוי הפונקציה להחזיר רשימה של TaskWithCount במקום Map
    @Query("SELECT status, COUNT(*) as count FROM tasks GROUP BY status")
    suspend fun getTaskStatistics(): List<TaskWithCount>
}

// הוספת data class לייצוג התוצאה
data class TaskWithCount(
    @ColumnInfo(name = "status") val status: TaskStatus,
    @ColumnInfo(name = "count") val count: Int
)
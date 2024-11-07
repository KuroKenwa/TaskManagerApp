package com.example.taskmanager.repository

import com.example.taskmanager.data.TaskDao
import com.example.taskmanager.data.TaskWithCount
import com.example.taskmanager.model.Task
import com.example.taskmanager.model.TaskStatus
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(
    private val taskDao: TaskDao
) {
    fun getAllTasks(): Flow<List<Task>> = taskDao.getAllTasks()

    suspend fun insertTask(task: Task) = taskDao.insertTask(task)

    suspend fun updateTask(task: Task) = taskDao.updateTask(task)

    suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)

    suspend fun getTaskById(id: Int): Task? = taskDao.getTaskById(id)

    suspend fun getStatistics(): Map<TaskStatus, Int> {
        return taskDao.getTaskStatistics()
            .associate { it.status to it.count }
    }
}
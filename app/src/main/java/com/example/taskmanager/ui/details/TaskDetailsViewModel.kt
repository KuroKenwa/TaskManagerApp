package com.example.taskmanager.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.model.Task
import com.example.taskmanager.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskDetailsViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {

    private val _task = MutableStateFlow<Task?>(null)
    val task: StateFlow<Task?> = _task

    fun loadTask(taskId: Int) {
        viewModelScope.launch {
            try {
                val loadedTask = repository.getTaskById(taskId)
                _task.value = loadedTask
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            try {
                repository.updateTask(task)
                _task.value = task
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
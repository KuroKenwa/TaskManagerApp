package com.example.taskmanager.ui.statistics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanager.model.TaskStatus
import com.example.taskmanager.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {

    private val _statistics = MutableStateFlow<Map<TaskStatus, Int>>(emptyMap())
    val statistics: StateFlow<Map<TaskStatus, Int>> = _statistics

    init {
        loadStatistics()
    }

    private fun loadStatistics() {
        viewModelScope.launch {
            try {
                val stats = repository.getStatistics()
                _statistics.value = stats
            } catch (e: Exception) {
                // Handle error - maybe emit to UI
                _statistics.value = emptyMap()
            }
        }
    }

    fun refreshStatistics() {
        loadStatistics()
    }
}
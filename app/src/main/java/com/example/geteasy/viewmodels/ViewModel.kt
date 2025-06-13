package com.example.geteasy.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geteasy.data.local.entities.DailyTask
import com.example.geteasy.data.repository.DailyTaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import java.util.Calendar

@HiltViewModel
class DailyTaskViewModel @Inject constructor(
    private val repository: DailyTaskRepository
) : ViewModel() {

    val tasks: Flow<List<DailyTask>> = repository.getTasksForDay(
        currentDate = System.currentTimeMillis(),
        dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
    )

    fun addTask(task: DailyTask) = viewModelScope.launch {
        repository.addTask(task)
    }

    fun deleteTask(task: DailyTask) = viewModelScope.launch {
        repository.deleteTask(task.id)
    }

    fun updateTask(task: DailyTask) = viewModelScope.launch {
        repository.updateTask(task)
    }

    private fun getCurrentDayOfWeek(): Int {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
    }
}
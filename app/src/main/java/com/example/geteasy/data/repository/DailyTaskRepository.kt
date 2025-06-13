package com.example.geteasy.data.repository

import com.example.geteasy.data.local.dao.DailyTaskDao
import com.example.geteasy.data.local.entities.DailyTask
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DailyTaskRepository @Inject constructor(
    private val dailyTaskDao: DailyTaskDao
) {
    fun getTasksForDay(currentDate: Long, dayOfWeek: Int): Flow<List<DailyTask>> =
        dailyTaskDao.getTasksForDay(currentDate, dayOfWeek)

    suspend fun addTask(task: DailyTask) = dailyTaskDao.insert(task)

    suspend fun updateTask(task: DailyTask) = dailyTaskDao.update(task)

    suspend fun deleteTask(id: Int) = dailyTaskDao.delete(id)

    suspend fun setTaskCompleted(id: Int, completed: Boolean) =
        dailyTaskDao.setCompleted(id, completed)

    suspend fun getTask(id: Int): DailyTask? = dailyTaskDao.getTaskById(id)
}
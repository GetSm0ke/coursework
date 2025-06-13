package com.example.geteasy.data.repository

import com.example.geteasy.data.local.entities.DailyTask
import com.example.geteasy.data.local.dao.DailyTaskDao
import kotlinx.coroutines.flow.Flow

class DailyTaskRepository(private val dailyTaskDao: DailyTaskDao) {
    fun getTasksForDay(dayOfWeek: Int): Flow<List<DailyTask>> {
        return dailyTaskDao.getTasksForDay(
            currentDate = System.currentTimeMillis(),
            dayOfWeek = dayOfWeek
        )
    }

    suspend fun addTask(task: DailyTask) = dailyTaskDao.insert(task)
    suspend fun deleteTask(id: Int) = dailyTaskDao.delete(id)
}
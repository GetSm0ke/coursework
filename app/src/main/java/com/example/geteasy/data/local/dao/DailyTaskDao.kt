package com.example.geteasy.data.local.dao

import androidx.room.*
import com.example.geteasy.data.local.entities.DailyTask
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyTaskDao {
    @Insert
    suspend fun insert(task: DailyTask)

    @Query("UPDATE daily_tasks SET isCompleted = :isCompleted WHERE id = :id")
    suspend fun setCompleted(id: Int, isCompleted: Boolean)

//    @Query("SELECT * FROM daily_tasks WHERE date(:currentDate) = date(reminderTime) OR repeatDays LIKE '%' || :dayOfWeek || '%'")
//    fun getTasksForDay(currentDate: Long, dayOfWeek: Int): Flow<List<DailyTask>>
    @Query("SELECT * FROM daily_tasks WHERE reminderTime = :currentDate")
    fun getTasksForDay(currentDate: Long): Flow<List<DailyTask>>

    @Query("DELETE FROM daily_tasks WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM daily_tasks WHERE id = :id")
    suspend fun getTaskById(id: Int): DailyTask?

    @Update
    suspend fun update(task: DailyTask)

}
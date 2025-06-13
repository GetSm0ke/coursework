package com.example.geteasy.data.local.dao

import androidx.room.*
import com.example.geteasy.data.local.entities.Reminder
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {
    @Insert
    suspend fun insert(reminder: Reminder)

    @Query("SELECT * FROM reminders ORDER BY dateTime ASC")
    fun getAllReminders(): Flow<List<Reminder>>

    @Delete
    suspend fun delete(reminder: Reminder)
}
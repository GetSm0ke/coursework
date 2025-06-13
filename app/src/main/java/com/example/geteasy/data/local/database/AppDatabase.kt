package com.example.geteasy.data.local.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.example.geteasy.data.local.dao.ReminderDao
import com.example.geteasy.data.local.entities.Reminder
import com.example.geteasy.data.local.dao.NoteDao
import com.example.geteasy.data.local.entities.Note
import com.example.geteasy.data.local.dao.DailyTaskDao
import com.example.geteasy.data.local.entities.DailyTask

@Database(
    entities = [Note::class, DailyTask::class, Reminder::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun dailyTaskDao(): DailyTaskDao
    abstract fun reminderDao(): ReminderDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
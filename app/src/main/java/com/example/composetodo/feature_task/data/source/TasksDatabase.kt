package com.example.composetodo.feature_task.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.composetodo.feature_task.domain.model.Task

@Database(entities = [Task::class], version = 1)
abstract class TasksDatabase: RoomDatabase() {

    abstract fun tasksDao(): TasksDao

    companion object {
        const val DB_NAME = "tasks_database"
    }
}
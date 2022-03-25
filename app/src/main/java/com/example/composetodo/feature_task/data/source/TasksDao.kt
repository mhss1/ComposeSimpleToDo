package com.example.composetodo.feature_task.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.composetodo.feature_task.domain.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {

    @Query("SELECT * FROM tasks_table ORDER BY dueDate")
    fun getTasks(): Flow<List<Task>>

    @Query("SELECT * FROM tasks_table WHERE title LIKE '%' || :title || '%' ORDER BY dueDate")
    fun getTasksByTitle(title: String): Flow<List<Task>>

    @Insert
    suspend fun addTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

}
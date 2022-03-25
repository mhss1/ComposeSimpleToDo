package com.example.composetodo.feature_task.domain.repository

import com.example.composetodo.feature_task.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TasksRepository {

    suspend fun addTask(task: Task)

    suspend fun deleteTask(task: Task)

    fun getTasks() : Flow<List<Task>>
}
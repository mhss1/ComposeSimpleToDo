package com.example.composetodo.feature_task.domain.use_cases

import com.example.composetodo.feature_task.domain.model.Task
import com.example.composetodo.feature_task.domain.repository.TasksRepository
import kotlinx.coroutines.flow.Flow

class GetTasks(private val repository: TasksRepository) {

    operator fun invoke(): Flow<List<Task>>{
        return repository.getTasks()
    }
}
package com.example.composetodo.feature_task.domain.use_cases

import com.example.composetodo.feature_task.domain.model.Task
import com.example.composetodo.feature_task.domain.repository.TasksRepository

class DeleteTask(private val repository: TasksRepository) {

    suspend operator fun invoke(task: Task) {
        repository.deleteTask(task)
    }
}
package com.example.composetodo.feature_task.domain.use_cases

import com.example.composetodo.feature_task.domain.model.Task
import com.example.composetodo.feature_task.domain.repository.TasksRepository
import javax.inject.Inject

class AddTask @Inject constructor(
    private val repository: TasksRepository
    ) {

    suspend operator fun invoke(task: Task) {
        repository.addTask(task)
    }
}
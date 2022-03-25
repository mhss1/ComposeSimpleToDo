package com.example.composetodo.feature_task.domain.use_cases

data class TaskUseCases(
    val addTask: AddTask,
    val deleteTask: DeleteTask,
    val getTasks: GetTasks
)
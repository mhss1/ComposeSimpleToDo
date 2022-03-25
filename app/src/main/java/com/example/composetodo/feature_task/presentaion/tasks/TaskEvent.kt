package com.example.composetodo.feature_task.presentaion.tasks

import com.example.composetodo.feature_task.domain.model.Task

sealed class TaskEvent {
    data class DeleteTask(val task: Task): TaskEvent()
    object CancelDelete: TaskEvent()
    object RestoreTask: TaskEvent()
}
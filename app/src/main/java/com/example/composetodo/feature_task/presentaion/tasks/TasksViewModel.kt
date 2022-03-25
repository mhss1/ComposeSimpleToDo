package com.example.composetodo.feature_task.presentaion.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composetodo.feature_task.domain.model.Task
import com.example.composetodo.feature_task.domain.use_cases.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val useCases: TaskUseCases
) : ViewModel() {

    val allTasks = useCases.getTasks()

    private var recentlyDeletedTask: Task? = null

    private var deleteTaskJob: Job? = null

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: TaskEvent) {
        when (event) {
            is TaskEvent.DeleteTask -> {
                viewModelScope.launch {
                    deleteTask(event.task)
                }
            }
            is TaskEvent.RestoreTask -> {
                viewModelScope.launch {
                    useCases.addTask(recentlyDeletedTask ?: return@launch)
                    recentlyDeletedTask = null
                }
            }
            TaskEvent.CancelDelete -> {
                deleteTaskJob?.cancel()
            }
        }
    }

    private fun deleteTask(task: Task){
        deleteTaskJob = viewModelScope.launch {
            delay(1000L)
            useCases.deleteTask(task)
            recentlyDeletedTask = task
            _eventFlow.emit(
                UiEvent.ShowSnackBar
            )
        }
    }

    sealed class UiEvent {
        object ShowSnackBar : UiEvent()
    }
}
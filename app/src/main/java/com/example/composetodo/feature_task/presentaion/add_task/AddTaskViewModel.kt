package com.example.composetodo.feature_task.presentaion.add_task

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composetodo.feature_task.domain.model.Task
import com.example.composetodo.feature_task.domain.use_cases.AddTask
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val addTask: AddTask
) : ViewModel() {

    private val _title = mutableStateOf("")
    val title: State<String> = _title
    private val _color = mutableStateOf(Color.DarkGray.toArgb())
    val color: State<Int> = _color
    private val _dueDate = mutableStateOf(System.currentTimeMillis())
    val dueDate: State<Long> = _dueDate

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: AddTaskEvent) {
        when (event) {
            is AddTaskEvent.SaveTask -> {
                viewModelScope.launch {
                    if (title.value.isNotBlank()) {
                        addTask(
                            Task(
                                _title.value,
                                _dueDate.value,
                                _color.value
                            )
                        )
                        _eventFlow.emit(UiEvent.TaskSaved)
                    } else
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                "Title can not be empty"
                            )
                        )

                }
            }
            is AddTaskEvent.ColorChanged -> _color.value = event.color
            is AddTaskEvent.DateChanged -> _dueDate.value = event.date
            is AddTaskEvent.TitleChanged -> _title.value = event.title
        }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        object TaskSaved : UiEvent()
    }
}
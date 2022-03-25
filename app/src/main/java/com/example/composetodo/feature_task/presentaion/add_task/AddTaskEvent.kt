package com.example.composetodo.feature_task.presentaion.add_task


sealed class AddTaskEvent{
    data class TitleChanged(val title: String): AddTaskEvent()
    data class DateChanged(val date: Long): AddTaskEvent()
    data class ColorChanged(val color: Int): AddTaskEvent()
    object SaveTask: AddTaskEvent()
}
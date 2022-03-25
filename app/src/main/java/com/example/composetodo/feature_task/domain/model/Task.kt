package com.example.composetodo.feature_task.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tasks_table")
data class Task(
    val title: String,
    val dueDate: Long,
    val color: Int,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
): Parcelable
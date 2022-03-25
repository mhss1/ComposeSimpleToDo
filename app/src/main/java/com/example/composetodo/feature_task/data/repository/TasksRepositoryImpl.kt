package com.example.composetodo.feature_task.data.repository

import com.example.composetodo.feature_task.data.source.TasksDao
import com.example.composetodo.feature_task.domain.model.Task
import com.example.composetodo.feature_task.domain.repository.TasksRepository
import kotlinx.coroutines.flow.Flow

class TasksRepositoryImpl (private val tasksDao: TasksDao) : TasksRepository {

    override suspend fun addTask(task: Task) {
        tasksDao.addTask(task)
    }

    override suspend fun deleteTask(task: Task) {
        tasksDao.deleteTask(task)
    }

    override fun getTasks(): Flow<List<Task>> {
        return tasksDao.getTasks()
    }
}
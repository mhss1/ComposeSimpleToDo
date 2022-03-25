package com.example.composetodo.di

import android.app.Application
import androidx.room.Room
import com.example.composetodo.feature_task.data.repository.TasksRepositoryImpl
import com.example.composetodo.feature_task.data.source.TasksDatabase
import com.example.composetodo.feature_task.domain.model.Task
import com.example.composetodo.feature_task.domain.repository.TasksRepository
import com.example.composetodo.feature_task.domain.use_cases.AddTask
import com.example.composetodo.feature_task.domain.use_cases.DeleteTask
import com.example.composetodo.feature_task.domain.use_cases.GetTasks
import com.example.composetodo.feature_task.domain.use_cases.TaskUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room.databaseBuilder(
        app,
        TasksDatabase::class.java,
        TasksDatabase.DB_NAME
    ).build()

    @Provides
    @Singleton
    fun provideNoteRepository(db: TasksDatabase): TasksRepository = TasksRepositoryImpl(db.tasksDao())

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: TasksRepository)
        = TaskUseCases(
            addTask = AddTask(repository),
            deleteTask = DeleteTask(repository),
            getTasks = GetTasks(repository)
        )
}
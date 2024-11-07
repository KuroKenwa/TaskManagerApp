package com.example.taskmanager.di

import android.content.Context
import com.example.taskmanager.data.TaskDao
import com.example.taskmanager.data.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    // הסרת provideDatabase מכאן כי הוא כבר קיים ב-DatabaseModule

    @Provides
    @Singleton
    fun provideTaskDao(database: TaskDatabase): TaskDao {
        return database.taskDao()
    }
}
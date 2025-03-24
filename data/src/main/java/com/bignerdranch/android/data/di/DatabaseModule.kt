package com.bignerdranch.android.data.di

import android.content.Context
import androidx.room.Room
import com.bignerdranch.android.data.local.dao.CourseDao
import com.bignerdranch.android.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCourseDao(appDatabase: AppDatabase): CourseDao {
        return appDatabase.courseDao()
    }
}
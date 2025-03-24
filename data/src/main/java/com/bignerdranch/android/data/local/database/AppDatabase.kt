package com.bignerdranch.android.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bignerdranch.android.data.local.dao.CourseDao
import com.bignerdranch.android.data.local.entity.CourseEntity


@Database(
    entities = [CourseEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun courseDao(): CourseDao
}
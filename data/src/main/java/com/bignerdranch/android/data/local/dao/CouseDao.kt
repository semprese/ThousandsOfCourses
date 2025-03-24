package com.bignerdranch.android.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bignerdranch.android.data.local.entity.CourseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {
    @Insert
    suspend fun insert(course: CourseEntity)

    @Delete
    suspend fun delete(course: CourseEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM course_table WHERE id = :id LIMIT 1)")
    suspend fun doesIdExist(id: Int): Boolean

    @Query("SELECT id FROM course_table")
    fun getAllFavorites(): Flow<List<Int>>
}
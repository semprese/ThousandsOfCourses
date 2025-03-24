package com.bignerdranch.android.data.repository

import com.bignerdranch.android.data.local.dao.CourseDao
import com.bignerdranch.android.data.local.entity.CourseEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CourseRepository @Inject constructor(
    private val courseDao: CourseDao
) {

    suspend fun insertCourse(course: CourseEntity) {
        courseDao.insert(course)
    }

    suspend fun deleteCourse(course: CourseEntity) {
        courseDao.delete(course)
    }

    suspend fun doesCourseExist(id: Int): Boolean {
        return courseDao.doesIdExist(id)
    }

    fun getAllFavorites(): Flow<List<Int>> {
        return courseDao.getAllFavorites()
    }
}
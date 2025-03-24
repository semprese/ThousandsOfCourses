package com.bignerdranch.android.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course_table")
data class CourseEntity(
    @PrimaryKey
    val id: Int
)
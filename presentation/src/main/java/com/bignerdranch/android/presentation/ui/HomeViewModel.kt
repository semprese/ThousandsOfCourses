package com.bignerdranch.android.presentation.ui
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.data.local.entity.CourseEntity
import com.bignerdranch.android.data.repository.AuthRepository
import com.bignerdranch.android.data.repository.CourseRepository
import com.bignerdranch.android.domain.model.Course
import com.bignerdranch.android.domain.model.LoginRequest
import com.bignerdranch.android.presentation.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel()
class HomeViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val courseRepository: CourseRepository
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    private var isSortDegreesState = mutableStateOf(false)

    fun toggleFavorite(courseId: Int, isChecked:Boolean) {
        viewModelScope.launch {
            when(isChecked){
                true -> {
                    if (!courseRepository.doesCourseExist(courseId)) {
                        courseRepository.insertCourse(CourseEntity(courseId))
                    }
                }
                false -> {
                    courseRepository.deleteCourse(CourseEntity(courseId))
                }
            }
        }
    }
    fun deleteFromFavorite(courseId: Int){
        viewModelScope.launch {
            courseRepository.deleteCourse(CourseEntity(courseId))

        }
    }

    fun onChangeSortType(isDegree:Boolean){
        println(isDegree)
        if (isDegree) {
            sortListByDate()
        }else{
            sortListByDefault()
        }
    }
    private fun sortListByDate() {
        when (val current = _loginState.value) {
            is LoginState.Success -> {
                _loginState.value = current.copy(
                    response = current.response
                        .sortedByDescending { it.course.startDate }
                )
            }
            else -> {}
        }
    }
    private fun sortListByDefault() {
        when (val current = _loginState.value) {
            is LoginState.Success -> {
                _loginState.value = current.copy(
                    response = current.response
                        .sortedBy { it.course.id }
                )
            }
            else -> {}
        }
    }


    private val listImg = listOf(R.drawable.course1, R.drawable.course2, R.drawable.course3)

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading

            try {
                val request = LoginRequest(username, password)

                //change hasLike from database
                val combinedFlow = combine(
                    authRepository.login(request),
                    courseRepository.getAllFavorites()
                ) { loginResponse , listFavorites ->
                    loginResponse.courses.map {
                        it.copy(hasLike = listFavorites.contains(it.id))
                    }
                }

                combinedFlow.collect { response ->
                    val listWithImg = response.mapIndexed { index, courseFromRequest ->
                        CoursesWithImg(
                            course = courseFromRequest,
                            imageResId = listImg[ index % 3 ]
                        ) }

                    _loginState.value = LoginState.Success(
                        if (isSortDegreesState.value) listWithImg.reversed() else listWithImg
                    )
                }
            } catch (e: Exception) {
                println(e.message)
                _loginState.value = LoginState.Error(e.message ?: "Unknown error")
            }
        }
    }
}


sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val response: List<CoursesWithImg>) : LoginState()
    data class Error(val message: String) : LoginState()
}

data class CoursesWithImg (
    val course: Course,
    val imageResId: Int
)

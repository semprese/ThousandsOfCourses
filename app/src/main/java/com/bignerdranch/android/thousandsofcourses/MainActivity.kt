package com.bignerdranch.android.thousandsofcourses

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
//import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.bignerdranch.android.presentation.logincompose.theme.ThousandsOfCoursesTheme
import com.bignerdranch.android.presentation.ui.HomeViewModel
import com.bignerdranch.android.presentation.ui.onboarding.OnboardingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        resetOnboardingScreen(this)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        setContentView(R.layout.activity_main)
        setupNavigation()

    }


    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        if (OnboardingFragment.shouldShow(this)) {
            navController.navigate(com.bignerdranch.android.presentation.R.id.onboardingFragment)
        }
    }

    private fun resetOnboardingScreen(context: Context) {
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            .edit()
            .putBoolean("welcome_shown", false)
            .apply()
    }
}


package com.bignerdranch.android.thousandsofcourses

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
//import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.bignerdranch.android.presentation.logincompose.theme.ThousandsOfCoursesTheme
import com.bignerdranch.android.presentation.ui.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        setContentView(R.layout.activity_main)

//        val navHostFragment = supportFragmentManager
//            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        val navController = navHostFragment.navController
//        setupActionBarWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}


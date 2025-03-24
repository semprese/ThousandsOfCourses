package com.bignerdranch.android.presentation.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bignerdranch.android.presentation.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class FullscreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fullscreen, container, false)

        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.nav_view)
        val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
        val navController = navHostFragment.findNavController()
//        val navController = findNavController()

        bottomNavigationView.setupWithNavController(navController)
        return view
    }


}
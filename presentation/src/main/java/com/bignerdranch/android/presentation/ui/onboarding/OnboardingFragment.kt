package com.bignerdranch.android.presentation.ui.onboarding

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.presentation.R


class OnboardingFragment() : Fragment() {

    private val sharedPrefs: SharedPreferences by lazy {
        requireContext().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_onboarding, container, false)
        val buttonOpenActivity: Button = view.findViewById<Button>(R.id.continueButton)

        buttonOpenActivity.setOnClickListener {
            findNavController().navigate(R.id.action_inboxFragment_to_loginFragment)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPrefs.edit().putBoolean("welcome_shown", true).apply()
    }

    companion object {
        fun shouldShow(context: Context): Boolean {
            val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            return !prefs.getBoolean("welcome_shown", false)
        }
    }
}


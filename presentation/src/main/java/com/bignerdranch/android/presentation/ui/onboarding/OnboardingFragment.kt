package com.bignerdranch.android.presentation.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.presentation.R


class OnboardingFragment() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_inbox, container, false)
        val buttonOpenActivity: Button = view.findViewById<Button>(R.id.continueButton)

        buttonOpenActivity.setOnClickListener {
            findNavController().navigate(R.id.action_inboxFragment_to_loginFragment)
        }

        return view
    }
}


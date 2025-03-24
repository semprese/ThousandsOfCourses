package com.bignerdranch.android.presentation.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bignerdranch.android.presentation.R
import com.bignerdranch.android.presentation.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        val view = inflater.inflate(R.layout.fragment_account, container, false)

        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root//

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
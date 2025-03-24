package com.bignerdranch.android.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.presentation.databinding.FragmentMainBinding
import com.bignerdranch.android.presentation.ui.CoursesWithImg
import com.bignerdranch.android.presentation.ui.HomeViewModel
import com.bignerdranch.android.presentation.ui.LoginState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainScreenFragment : Fragment() {

    private val viewModel: HomeViewModel by activityViewModels<HomeViewModel>()

    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView = binding.recyclerView
        binding.sortButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) binding.sortButton.text = "По популярности"
            else binding.sortButton.text = "По дате добавления"
            viewModel.onChangeSortType(isChecked)
        }

        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CourseAdapter { courseId, isChecked ->
            viewModel.toggleFavorite(courseId, isChecked)
        }
        recyclerView.adapter = adapter

        layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        lifecycleScope.launch {
            viewModel.loginState.flowWithLifecycle(lifecycle)
                .collect { state ->
                    when (state) {
                        is LoginState.Success -> {
                            adapter.updateCourses(state.response)
                            adapter.notifyDataSetChanged()
                        }

                        else -> {}
                    }
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.bignerdranch.android.presentation.ui.favorite

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
import com.bignerdranch.android.presentation.databinding.FragmentFavoriteBinding
import com.bignerdranch.android.presentation.ui.HomeViewModel
import com.bignerdranch.android.presentation.ui.LoginState
import com.bignerdranch.android.presentation.ui.home.CourseAdapter
import kotlinx.coroutines.launch

class FavoriteFragment : Fragment() {

    private val viewModel: HomeViewModel by activityViewModels<HomeViewModel>()

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        binding.favoriteRecyclerView.layoutManager = LinearLayoutManager(context)
            .apply {
                recycleChildrenOnDetach = true
            }
        recyclerView = binding.favoriteRecyclerView

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CourseAdapter { courseId, isChecked ->
            viewModel.deleteFromFavorite(courseId)
        }
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.loginState.flowWithLifecycle(lifecycle)
                .collect { state ->
                    when (state) {
                        is LoginState.Success -> {
                            adapter.updateCourses(state.response.filter { it.course.hasLike })
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
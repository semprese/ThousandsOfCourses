package com.bignerdranch.android.presentation.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.presentation.R
import com.bignerdranch.android.presentation.ui.CoursesWithImg
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter


abstract class CourseAdapterDelegate : AdapterDelegate<List<CoursesWithImg>>() {
    override fun isForViewType(items: List<CoursesWithImg>, position: Int): Boolean {
        return true
    }
}

class CourseItemDelegate(
    private val onFavoriteCheckedChange: (Int, Boolean) -> Unit = { _, _ -> },
) : CourseAdapterDelegate() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_course, parent, false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(
        items: List<CoursesWithImg>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>,
    ) {
        val course = items[position]
        val viewHolder = holder as CourseViewHolder

        if (payloads.isNotEmpty()) {
            payloads.forEach { payload ->
                if (payload is Boolean) {
                    viewHolder.favoriteCheckBox.isChecked = payload
                    return
                }
            }
            return
        }
        viewHolder.bind(course, onFavoriteCheckedChange)
    }

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val textTextView: TextView = itemView.findViewById(R.id.textTextView)
        val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
        val rateTextView: TextView = itemView.findViewById(R.id.rateTextView)
        val startDateTextView: TextView = itemView.findViewById(R.id.startDateTextView)
        val favoriteCheckBox: CheckBox = itemView.findViewById(R.id.favoriteButton)
        val courseImageView: ImageView = itemView.findViewById(R.id.courseImageView)

        fun bind(coursesWithImg: CoursesWithImg, onFavoriteCheckedChange: (Int, Boolean) -> Unit) {
            titleTextView.text = coursesWithImg.course.title
            textTextView.text = correctDescription(coursesWithImg.course.text)
            priceTextView.text = coursesWithImg.course.price + " ₽"
            rateTextView.text = coursesWithImg.course.rate
            startDateTextView.text = coursesWithImg.course.startDate
            favoriteCheckBox.isChecked = coursesWithImg.course.hasLike
            courseImageView.setImageResource(coursesWithImg.imageResId)

            favoriteCheckBox.setOnCheckedChangeListener(null)
            favoriteCheckBox.setOnCheckedChangeListener { _, isChecked ->
                onFavoriteCheckedChange(coursesWithImg.course.id, isChecked)
            }
        }

        private fun correctDescription(text: String): String {
            return if (text.length < 80) text else text.take(65) + "..."
        }
    }
}

class CourseAdapter(
    onFavoriteCheckedChange: (Int, Boolean) -> Unit = { _, _ -> },
) : ListDelegationAdapter<List<CoursesWithImg>>() {

    init {
        delegatesManager.addDelegate(
            CourseItemDelegate(onFavoriteCheckedChange)
        )
    }

    fun updateCourses(newCourses: List<CoursesWithImg>) {
        items = newCourses
    }

    fun updateFavoriteStatus(courseId: Int, isFavorite: Boolean) {
        val position = items?.indexOfFirst { it.course.id == courseId } ?: -1
        if (position >= 0) {
            notifyItemRemoved(position)
        }
    }
}
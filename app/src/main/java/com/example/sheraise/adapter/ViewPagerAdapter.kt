package com.example.sheraise.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.sheraise.fragment.AboutFragment
import com.example.sheraise.fragment.ArticleFragment
import com.example.sheraise.fragment.VideoFragment

class ViewPagerAdapter(
    fragment: Fragment,
    private val courseId: String
) : FragmentStateAdapter(fragment) {

    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AboutFragment.newInstance(courseId)
            1 -> VideoFragment.newInstance(courseId)
            2 -> ArticleFragment.newInstance(courseId)
            else -> AboutFragment.newInstance(courseId)
        }
    }
}


package com.example.sheraise.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.sheraise.fragment.AboutFragment
import com.example.sheraise.fragment.ArticleFragment
import com.example.sheraise.fragment.VideoFragment

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AboutFragment()
            1 -> VideoFragment()
            2 -> ArticleFragment()
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }
}

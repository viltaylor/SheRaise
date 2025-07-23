package com.example.sheraise

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class HomeMentorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_mentor)

        val navItems = listOf(
            Triple(R.id.navHome, R.id.navHomeIcon, R.id.navHomeLabel to R.id.navHomeCircle),
            Triple(R.id.navCourse, R.id.navCourseIcon, R.id.navCourseLabel to R.id.navCourseCircle),
            Triple(R.id.navFriends, R.id.navFriendIcon, R.id.navFriendsLabel to R.id.navFriendsCircle),
            Triple(R.id.navRequest, R.id.navRequestIcon, R.id.navRequestLabel to R.id.navRequestCircle),
            Triple(R.id.navCalendar, R.id.navCalendarIcon, R.id.navCalendarLabel to R.id.navCalendarCircle)
        )

        navItems.forEach { (layoutId, _, _) ->
            val layout = findViewById<LinearLayout>(layoutId)
            layout.setOnClickListener {
                selectNavItem(layoutId)
                when (layoutId) {
                    R.id.navHome -> replaceFragment(HomeMentorFragment())
                    R.id.navCourse -> replaceFragment(CourseMentorFragment())
                    R.id.navFriends -> replaceFragment(FriendsMentorFragment())
                    R.id.navRequest -> replaceFragment(RequestMentorFragment())
                    R.id.navCalendar -> replaceFragment(CalendarMentorFragment())
                }
            }
        }

        // Default fragment and selection
        replaceFragment(HomeMentorFragment())
        selectNavItem(R.id.navHome)
    }

    private fun selectNavItem(selectedId: Int) {
        val navItemTuples = listOf(
            Triple(R.id.navHome, R.id.navHomeIcon, R.id.navHomeLabel to R.id.navHomeCircle),
            Triple(R.id.navCourse, R.id.navCourseIcon, R.id.navCourseLabel to R.id.navCourseCircle),
            Triple(R.id.navFriends, R.id.navFriendIcon, R.id.navFriendsLabel to R.id.navFriendsCircle),
            Triple(R.id.navRequest, R.id.navRequestIcon, R.id.navRequestLabel to R.id.navRequestCircle),
            Triple(R.id.navCalendar, R.id.navCalendarIcon, R.id.navCalendarLabel to R.id.navCalendarCircle)
        )

        navItemTuples.forEach { (layoutId, iconId, labelCirclePair) ->
            val layout = findViewById<LinearLayout>(layoutId)
            val icon = layout.findViewById<ImageView>(iconId)
            val label = layout.findViewById<TextView>(labelCirclePair.first)
            val circle = layout.findViewById<FrameLayout>(labelCirclePair.second)

            val isSelected = layoutId == selectedId
            animateNavItem(icon, label, circle, isSelected)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    private fun animateNavItem(
        iconView: ImageView,
        textView: TextView,
        circleView: FrameLayout,
        isSelected: Boolean
    ) {
        iconView.animate().cancel()
        textView.animate().cancel()
        circleView.animate().cancel()

        if (isSelected) {
            iconView.animate().scaleX(1.2f).scaleY(1.2f).setDuration(100).start()
            textView.visibility = View.VISIBLE
            textView.alpha = 0f
            textView.animate().alpha(1f).setDuration(100).start()

            circleView.visibility = View.VISIBLE
            circleView.setBackgroundResource(R.drawable.nav_selected)

            iconView.setColorFilter(ContextCompat.getColor(this, android.R.color.white))
            textView.setTextColor(ContextCompat.getColor(this, R.color.pink))
        } else {
            iconView.animate().scaleX(1f).scaleY(1f).setDuration(100).start()
            textView.animate().alpha(0f).setDuration(100).withEndAction {
                textView.visibility = View.GONE
            }.start()

            circleView.setBackgroundColor(Color.TRANSPARENT)

            iconView.setColorFilter(ContextCompat.getColor(this, android.R.color.black))
            textView.setTextColor(ContextCompat.getColor(this, android.R.color.black))
        }
    }
}

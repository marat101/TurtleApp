package com.turtleteam.widget_schedule.schedule_select.view

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.turtleteam.widget_schedule.schedule_select.SelectType
import com.turtleteam.widget_schedule.schedule_select.base.BaseSelectFragment
import com.turtleteam.widget_schedule.schedule_select.base.GroupSelectFragment
import com.turtleteam.widget_schedule.schedule_select.base.TeacherSelectFragment

class ViewPagerAdapter(manager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(manager, lifecycle) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            1 -> {
                GroupSelectFragment()
            }
            else -> {
                TeacherSelectFragment()
            }
        }
    }
}
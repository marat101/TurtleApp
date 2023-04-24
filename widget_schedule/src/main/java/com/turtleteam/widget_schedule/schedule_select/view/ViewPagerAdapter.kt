package com.turtleteam.widget_schedule.schedule_select.view

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.turtleteam.widget_schedule.schedule_select.SelectType
import com.turtleteam.widget_schedule.schedule_select.base.BaseSelectFragment

class ViewPagerAdapter(manager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(manager, lifecycle) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            1 -> {
                val fragment = BaseSelectFragment()
                fragment.arguments = bundleOf("group" to SelectType.GROUP.name)
                fragment
            }
            else -> {
                val fragment = BaseSelectFragment()
                fragment.arguments = bundleOf("teacher" to SelectType.TEACHER.name)
                fragment
            }
        }
    }
}
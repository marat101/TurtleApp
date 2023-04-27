package com.turtleteam.widget_schedule.schedule_select

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.turtleteam.widget_schedule.databinding.ActivitySelectNameBinding
import com.turtleteam.widget_schedule.schedule_select.view.ViewPagerAdapter

class ScheduleSelectActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectNameBinding
    private var adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectNameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout,
            binding.viewPager
        ) { tab, position ->
            when (position) {
                0 -> tab.text = "Группы"
                1 -> tab.text = "Преподаватели"
            }
        }.attach()
    }
}
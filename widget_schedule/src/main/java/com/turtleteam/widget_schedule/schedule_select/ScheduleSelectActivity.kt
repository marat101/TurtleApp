package com.turtleteam.widget_schedule.schedule_select

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
    }
}
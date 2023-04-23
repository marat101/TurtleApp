package com.turtleteam.widget_schedule.schedule_select.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.turtleteam.widget_schedule.databinding.FragmentNamesBinding
import com.turtleteam.widget_schedule.schedule_select.SelectType

class BaseSelectFragment<T: SelectType>: Fragment() {


    protected lateinit var binding: FragmentNamesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNamesBinding.inflate(inflater, container, false)
        return binding.root
    }
}
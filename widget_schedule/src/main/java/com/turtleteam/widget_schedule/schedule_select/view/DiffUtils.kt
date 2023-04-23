package com.turtleteam.widget_schedule.schedule_select.view

import androidx.recyclerview.widget.DiffUtil

class DiffUtils : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem.lowercase() == newItem.lowercase()
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem.lowercase() == newItem.lowercase()
    }
}
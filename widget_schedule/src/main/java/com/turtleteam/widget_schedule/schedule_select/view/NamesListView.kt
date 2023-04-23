package com.turtleteam.widget_schedule.schedule_select.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.turtleteam.domain.model.teachersandgroups.NamesList
import com.turtleteam.widget_schedule.R
import com.turtleteam.widget_schedule.databinding.LayoutHeaderItemBinding
import com.turtleteam.widget_schedule.databinding.LayoutNameItemBinding
import com.turtleteam.widget_schedule.schedule_select.SelectType

class NamesListView(private val type: SelectType, val onItemClick: (String) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list = mutableListOf<NameItemType>()

    private inner class NameItemView(private val binding: LayoutNameItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(name: String) = with(binding){
            nameItem.text = name
            nameButton.setOnClickListener {
                onItemClick(name)
            }
        }
    }

    private inner class HeaderItemView(private val binding: LayoutHeaderItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(header: String) = with(binding){
            headerText.text = header
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            R.layout.layout_name_item -> NameItemView(LayoutNameItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            R.layout.layout_header_item -> HeaderItemView(LayoutHeaderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> {throw IllegalAccessException("Unknown item type")}
        }
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is NameItemView -> holder.bind((list[position] as NameItemType.Name).name)
            is HeaderItemView -> holder.bind((list[position] as NameItemType.Header).header)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(list[position] is NameItemType.Name) R.layout.layout_name_item else R.layout.layout_header_item
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(names: NamesList){
        list.clear()
        if (names.pinned.isNotEmpty())
            list.add(NameItemType.Header("Закреплённые"))
        names.pinned.forEach {
            list.add(NameItemType.Name(it))
        }
        if (names.groups.isNotEmpty())
            list.add(NameItemType.Header(if (type == SelectType.GROUP)"Все группы" else "Все преподаватели"))
        names.groups.forEach {
            list.add(NameItemType.Name(it))
        }
        notifyDataSetChanged()
    }
}

sealed class NameItemType {
    data class Name(val name: String): NameItemType()

    data class Header(val header: String): NameItemType()
}
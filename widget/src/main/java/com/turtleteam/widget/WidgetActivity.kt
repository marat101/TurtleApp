package com.turtleteam.widget

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.turtleteam.widget.databinding.ActivityWidgetBinding
import com.turtleteam.widget.widget.utils.UpdateWidget
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class WidgetActivity : AppCompatActivity() {
    private val vModel by viewModel<WidgetActivityViewModel>()
    lateinit var binding: ActivityWidgetBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val wId = intent.extras?.getInt("id")?: throw IllegalArgumentException("require widget id")

        binding = ActivityWidgetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var list = listOf<String>()
        lifecycleScope.launch {
            vModel.namesFlow.collectLatest {
                list = it
                binding.grid.adapter =
                    ArrayAdapter(
                        this@WidgetActivity,
                        android.R.layout.simple_list_item_1,
                        it
                    )
            }
        }



        binding.grid.setOnItemClickListener { _, _, position, _ ->
            lifecycleScope.launch {
                vModel.setNewSchedule(list[position])
                UpdateWidget.Base(this@WidgetActivity,wId).fullUpdate()
                finish()
            }
        }
    }
}
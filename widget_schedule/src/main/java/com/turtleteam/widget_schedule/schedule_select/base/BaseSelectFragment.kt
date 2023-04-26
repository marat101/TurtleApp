package com.turtleteam.widget_schedule.schedule_select.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.turtleteam.widget_schedule.databinding.FragmentNamesBinding
import com.turtleteam.widget_schedule.schedule_select.SelectType
import com.turtleteam.widget_schedule.schedule_select.view.NamesListView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent
import org.koin.core.qualifier.named


class GroupSelectFragment() : BaseSelectFragment() {
    override val type: SelectType = SelectType.GROUP
    override val viewModel: BaseSelectViewModel by viewModel(named(type.name))
    override val listView: NamesListView = NamesListView(type) {
        viewModel.clickOnName(it)
        activity?.finish()
    }
}

class TeacherSelectFragment() : BaseSelectFragment() {
    override val type: SelectType = SelectType.TEACHER
    override val viewModel: BaseSelectViewModel by viewModel(named(type.name))
    override val listView: NamesListView = NamesListView(type) {
        viewModel.clickOnName(it)
        activity?.finish()
    }
}

abstract class BaseSelectFragment : Fragment(), KoinComponent {
    abstract val type: SelectType

    abstract val viewModel: BaseSelectViewModel
    lateinit var binding: FragmentNamesBinding
    abstract val listView: NamesListView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNamesBinding.inflate(inflater, container, false)
        binding.searchList.adapter = listView

        binding.searchList.layoutManager = if (type == SelectType.GROUP) GridLayoutManager(
            this.context,
            2,
            GridLayoutManager.VERTICAL,
            false
        ) else LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.state.collectLatest {
                it.data?.let { names ->
                    listView.setList(names)
                }
            }
        }
    }
}
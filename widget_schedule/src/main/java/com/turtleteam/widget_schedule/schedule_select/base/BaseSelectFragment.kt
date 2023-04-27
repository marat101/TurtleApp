package com.turtleteam.widget_schedule.schedule_select.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.turtleteam.domain.model.teachersandgroups.NamesList
import com.turtleteam.domain.utils.SearchNames
import com.turtleteam.widget_schedule.R
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
        initAdapter()
        initSearchListener()
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

    private fun initSearchListener(){
            binding.searchText.doAfterTextChanged {
                listView.setList(SearchNames.filterList(it.toString(),viewModel.state.value.data ?: NamesList.empty))
            }
    }

    private fun initAdapter(){
        binding.searchList.adapter = listView

        val layoutManager = if (type == SelectType.GROUP) {
            val manager = GridLayoutManager(
                this.context,
                4,
                GridLayoutManager.VERTICAL,
                false
            )
            manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (listView.getItemViewType(position)) {
                        R.layout.layout_header_item -> 4
                        else -> 2
                    }
                }
            }
            manager
        } else LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.searchList.layoutManager = layoutManager
    }
}
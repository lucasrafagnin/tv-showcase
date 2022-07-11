package com.rafagnin.tvshowcase.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.rafagnin.tvshowcase.databinding.FragmentScheduleBinding
import com.rafagnin.tvshowcase.domain.model.EpisodeModel
import com.rafagnin.tvshowcase.ext.gone
import com.rafagnin.tvshowcase.ext.show
import com.rafagnin.tvshowcase.presentation.action.ScheduleAction
import com.rafagnin.tvshowcase.presentation.action.ScheduleAction.Retry
import com.rafagnin.tvshowcase.presentation.activity.EpisodeActivity
import com.rafagnin.tvshowcase.presentation.adapter.ScheduleAdapter
import com.rafagnin.tvshowcase.presentation.state.ScheduleState
import com.rafagnin.tvshowcase.presentation.state.ScheduleState.*
import com.rafagnin.tvshowcase.presentation.viewmodel.ScheduleViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ScheduleFragment : Fragment(), ScheduleAdapter.AdapterCallback {

    private lateinit var binding: FragmentScheduleBinding
    private lateinit var viewModel: ScheduleViewModel
    private lateinit var adapter: ScheduleAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentScheduleBinding.inflate(inflater, container, false)
        this.binding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ScheduleViewModel::class.java]
        adapter = ScheduleAdapter(this)
        binding.list.adapter = adapter

        lifecycleScope.launchWhenCreated { viewModel._state.collect { render(it) } }

        binding.errorState.retry.setOnClickListener { click(Retry) }
    }

    override fun onEpisodeClick(model: EpisodeModel) = openDetailScreen(model)

    private fun render(state: ScheduleState) {
        binding.list.run { if (state is EpisodesLoaded) show() else gone() }
        binding.loading.run { if (state is Loading) show() else gone() }
        binding.errorState.root.run { if (state is Error) show() else gone() }

        if (state is EpisodesLoaded) adapter.update(state.items)
    }

    private fun openDetailScreen(model: EpisodeModel) {
        val intent = Intent(context, EpisodeActivity::class.java)
        intent.putExtra(EpisodeActivity.ID_EXTRA, model)
        startActivity(intent)
    }

    private fun click(action: ScheduleAction) {
        lifecycleScope.launch {
            viewModel.actionFlow.emit(action)
        }
    }

    companion object {
        const val SHORTCUT = "com.rafagnin.tvshowcase.schedule"
    }
}

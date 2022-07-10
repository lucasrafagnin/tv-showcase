package com.rafagnin.tvshowcase.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.rafagnin.tvshowcase.databinding.FragmentFavoritesBinding
import com.rafagnin.tvshowcase.ext.gone
import com.rafagnin.tvshowcase.ext.show
import com.rafagnin.tvshowcase.presentation.action.FavoritesAction.Retry
import com.rafagnin.tvshowcase.presentation.activity.DetailActivity
import com.rafagnin.tvshowcase.presentation.adapter.ShowsAdapter
import com.rafagnin.tvshowcase.presentation.state.FavoritesState
import com.rafagnin.tvshowcase.presentation.state.FavoritesState.*
import com.rafagnin.tvshowcase.presentation.viewmodel.FavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : Fragment(), ShowsAdapter.AdapterCallback {

    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var viewModel: FavoritesViewModel
    private lateinit var adapter: ShowsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        this.binding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[FavoritesViewModel::class.java]
        adapter = ShowsAdapter(this)
        binding.list.adapter = adapter

        lifecycleScope.launchWhenCreated {
            viewModel._state.collect { render(it) }
        }

        binding.errorState.retry.setOnClickListener {
            lifecycleScope.launch {
                viewModel.actionFlow.emit(Retry)
            }
        }
    }

    override fun onShowClick(id: Long) = openDetailScreen(id)

    private fun render(state: FavoritesState) {
        binding.list.run { if (state is ShowsLoaded) show() else gone() }
        binding.loading.run { if (state is Loading) show() else gone() }
        binding.errorState.root.run { if (state is Error) show() else gone() }
        binding.emptyState.root.run { if (state is Empty) show() else gone() }

        if (state is ShowsLoaded) adapter.update(state.items)
    }

    private fun openDetailScreen(id: Long) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.ID_EXTRA, id)
        startActivity(intent)
    }
}

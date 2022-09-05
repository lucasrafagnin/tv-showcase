package com.rafagnin.tvshowcase.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.rafagnin.tvshowcase.databinding.FragmentProfileBinding
import com.rafagnin.tvshowcase.ext.gone
import com.rafagnin.tvshowcase.ext.show
import com.rafagnin.tvshowcase.presentation.action.FavoritesAction.Retry
import com.rafagnin.tvshowcase.presentation.activity.ShowDetailActivity
import com.rafagnin.tvshowcase.presentation.adapter.ShowsAdapter
import com.rafagnin.tvshowcase.presentation.state.FavoritesState
import com.rafagnin.tvshowcase.presentation.state.FavoritesState.*
import com.rafagnin.tvshowcase.presentation.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment(), ShowsAdapter.AdapterCallback {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel
    private lateinit var favoritesAdapter: ShowsAdapter
    private lateinit var watchingAdapter: ShowsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentProfileBinding.inflate(inflater, container, false)
        this.binding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ProfileViewModel::class.java]
        favoritesAdapter = ShowsAdapter(this)
        watchingAdapter = ShowsAdapter(this)
        binding.watching.adapter = watchingAdapter
        binding.favorites.adapter = favoritesAdapter

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
        binding.watching.run { if (state is ShowsLoaded) show() }
        binding.favorites.run { if (state is ShowsLoaded) show() }
        binding.loading.run { if (state is Loading) show() else gone() }
        binding.errorState.root.run { if (state is Error) show() else gone() }
        binding.emptyState.root.run { if (state is Empty) show() else gone() }

        if (state is ShowsLoaded) favoritesAdapter.update(state.favorites)
        if (state is ShowsLoaded) watchingAdapter.update(state.addedShows)
    }

    private fun openDetailScreen(id: Long) {
        val intent = Intent(context, ShowDetailActivity::class.java)
        intent.putExtra(ShowDetailActivity.ID_EXTRA, id)
        startActivity(intent)
    }

    companion object {
        const val SHORTCUT = "com.rafagnin.tvshowcase.favorites"
    }
}

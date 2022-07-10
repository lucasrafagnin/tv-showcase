package com.rafagnin.tvshowcase.presentation.activity

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.rafagnin.tvshowcase.R
import com.rafagnin.tvshowcase.databinding.ActivitySearchBinding
import com.rafagnin.tvshowcase.ext.gone
import com.rafagnin.tvshowcase.ext.show
import com.rafagnin.tvshowcase.presentation.action.SearchAction
import com.rafagnin.tvshowcase.presentation.adapter.ShowsAdapter
import com.rafagnin.tvshowcase.presentation.state.SearchState
import com.rafagnin.tvshowcase.presentation.state.SearchState.Loading
import com.rafagnin.tvshowcase.presentation.state.SearchState.ShowsLoaded
import com.rafagnin.tvshowcase.presentation.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchActivity : AppCompatActivity(), ShowsAdapter.AdapterCallback {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var viewModel: SearchViewModel
    private lateinit var adapter: ShowsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ShowsAdapter(this)
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        binding.list.adapter = adapter

        setupTopBar()
        handleIntent(intent)
        lifecycleScope.launchWhenCreated { viewModel._state.collect { render(it) } }

        binding.errorState.retry.setOnClickListener {
            lifecycleScope.launch {
                viewModel.actionFlow.emit(SearchAction.Retry)
            }
        }
    }

    override fun onShowClick(id: Long) = openDetailScreen(id)

    private fun setupTopBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun render(state: SearchState) = when (state) {
        is ShowsLoaded -> {
            adapter.update(state.items)
            binding.toolbar.title = getString(R.string.search_result, state.query)
            binding.list.show()
            binding.loading.gone()
            binding.errorState.root.gone()
        }
        is Loading -> {
            binding.list.gone()
            binding.loading.show()
            binding.errorState.root.gone()
        }
        else -> {
            binding.list.gone()
            binding.loading.gone()
            binding.errorState.root.show()
        }
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.let {
                lifecycleScope.launch {
                    viewModel.actionFlow.emit(SearchAction.Query(it))
                }
            }
        }
    }

    private fun openDetailScreen(id: Long) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.ID_EXTRA, id)
        startActivity(intent)
    }
}

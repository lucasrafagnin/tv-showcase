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
import com.rafagnin.tvshowcase.presentation.fragment.ShowDetailFragment
import com.rafagnin.tvshowcase.presentation.state.SearchState
import com.rafagnin.tvshowcase.presentation.state.SearchState.Error
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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupTopBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun render(state: SearchState) {
        binding.list.run { if (state is ShowsLoaded) show() else gone() }
        binding.toolbar.run { if (state is ShowsLoaded) title = getString(R.string.search_result, state.query) }
        binding.loading.run { if (state is Loading) show() else gone() }
        binding.errorState.root.run { if (state is Error) show() else gone() }

        if (state is ShowsLoaded) adapter.update(state.items)
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
//        val intent = Intent(this, ShowDetailFragment::class.java)
//        intent.putExtra(ShowDetailFragment.ID_EXTRA, id)
//        startActivity(intent)
    }
}

package com.rafagnin.tvshowcase.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState.Error
import androidx.paging.LoadState.Loading
import androidx.paging.LoadState.NotLoading
import androidx.paging.cachedIn
import com.rafagnin.tvshowcase.databinding.FragmentDiscoveryBinding
import com.rafagnin.tvshowcase.ext.gone
import com.rafagnin.tvshowcase.ext.show
import com.rafagnin.tvshowcase.presentation.activity.ShowDetailActivity
import com.rafagnin.tvshowcase.presentation.adapter.ShowsPagingAdapter
import com.rafagnin.tvshowcase.presentation.viewmodel.DiscoveryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DiscoveryFragment : Fragment(), ShowsPagingAdapter.AdapterCallback {

    private lateinit var binding: FragmentDiscoveryBinding
    private lateinit var viewModel: DiscoveryViewModel
    private lateinit var adapter: ShowsPagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDiscoveryBinding.inflate(inflater, container, false)
        this.binding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[DiscoveryViewModel::class.java]
        adapter = ShowsPagingAdapter(this)
        binding.list.adapter = adapter
        binding.errorState.retry.setOnClickListener { adapter.retry() }

        render()
    }

    override fun onShowClick(id: Long) = openDetailScreen(id)

    private fun render() {
        lifecycleScope.launch {
            viewModel.getAllShows()
                .cachedIn(viewModel.viewModelScope)
                .collectLatest { adapter.submitData(it) }
        }
        lifecycleScope.launch {
            adapter.loadStateFlow
                .collectLatest {
                    it.refresh.let {
                        binding.list.run { if (it is NotLoading) show() else gone() }
                        binding.loading.run { if (it is Loading) show() else gone() }
                        binding.errorState.root.run { if (it is Error) show() else gone() }
                    }
                }
        }
    }

    private fun openDetailScreen(id: Long) {
        val intent = Intent(context, ShowDetailActivity::class.java)
        intent.putExtra(ShowDetailActivity.ID_EXTRA, id)
        startActivity(intent)
    }

    companion object {
        const val SHORTCUT = "com.rafagnin.tvshowcase.home"
    }
}

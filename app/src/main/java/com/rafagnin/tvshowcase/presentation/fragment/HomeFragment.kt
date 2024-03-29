package com.rafagnin.tvshowcase.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState.Error
import androidx.paging.LoadState.Loading
import androidx.paging.LoadState.NotLoading
import androidx.paging.cachedIn
import com.rafagnin.tvshowcase.NavGraphDirections
import com.rafagnin.tvshowcase.databinding.FragmentHomeBinding
import com.rafagnin.tvshowcase.ext.gone
import com.rafagnin.tvshowcase.ext.show
import com.rafagnin.tvshowcase.presentation.adapter.ShowsPagingAdapter
import com.rafagnin.tvshowcase.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), ShowsPagingAdapter.AdapterCallback {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: ShowsPagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        this.binding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
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
        findNavController().navigate(
            NavGraphDirections.appToShowdetail(id)
        )
    }

    companion object {
        const val SHORTCUT = "com.rafagnin.tvshowcase.home"
    }
}

package com.rafagnin.tvshowcase.presentation.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.rafagnin.tvshowcase.NavGraphDirections
import com.rafagnin.tvshowcase.R
import com.rafagnin.tvshowcase.databinding.FragmentShowDetailBinding
import com.rafagnin.tvshowcase.domain.model.CharacterModel
import com.rafagnin.tvshowcase.domain.model.EpisodeModel
import com.rafagnin.tvshowcase.domain.model.ShowDetailModel
import com.rafagnin.tvshowcase.ext.gone
import com.rafagnin.tvshowcase.ext.show
import com.rafagnin.tvshowcase.presentation.action.ShowDetailAction
import com.rafagnin.tvshowcase.presentation.activity.CharacterActivity
import com.rafagnin.tvshowcase.presentation.adapter.SeasonAdapter
import com.rafagnin.tvshowcase.presentation.state.ShowDetailState
import com.rafagnin.tvshowcase.presentation.state.ShowDetailState.Error
import com.rafagnin.tvshowcase.presentation.state.ShowDetailState.Loaded
import com.rafagnin.tvshowcase.presentation.state.ShowDetailState.Loading
import com.rafagnin.tvshowcase.presentation.viewmodel.ShowDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ShowDetailFragment : Fragment() {

    companion object {
        const val ID_EXTRA = "ID_EXTRA"
    }

    private lateinit var binding: FragmentShowDetailBinding
    private lateinit var viewModel: ShowDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentShowDetailBinding.inflate(inflater, container, false)
        this.binding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments ?: throw IllegalStateException("Show ID is required")
        val detailId = ShowDetailFragmentArgs.fromBundle(bundle).showId

        viewModel = ViewModelProvider(requireActivity())[ShowDetailViewModel::class.java]
        viewModel.getShowDetail(detailId)
        lifecycleScope.launchWhenCreated { viewModel._state.collect { render(it) } }

        binding.error.retry.setOnClickListener {
            lifecycleScope.launch {
                viewModel.actionFlow.emit(ShowDetailAction.Retry(detailId))
            }
        }
    }

    private fun render(state: ShowDetailState) {
        binding.content.run { if (state is Loaded) show() else gone() }
        binding.loading.run { if (state is Loading) show() else gone() }
        binding.error.root.run { if (state is Error) show() else gone() }

        if (state is Loaded) setupView(state.show)
    }

    private fun setupView(show: ShowDetailModel?) = show?.let {
        setFavorite(show)
        binding.poster.load(it.image) {
            transformations(RoundedCornersTransformation(10f))
        }
        binding.genres.text = it.genres
        binding.rate.text = it.rating.toString()
        binding.status.text = it.status
        binding.time.text = it.time
        binding.network.text = it.network
        it.averageRuntime?.let {
            binding.averageRuntime.text = getString(R.string.common_minutes, it)
            binding.averageRuntime.show()
        }
        setDescription(show.description)
        setCast(show.characters)
        setSeasons(show.seasons)
    }

    private fun setFavorite(show: ShowDetailModel) = with(binding.favorite) {
        setImageResource(if (show.favorite) R.drawable.ic_favorite else R.drawable.ic_unfavorite)
        setOnClickListener {
            lifecycleScope.launch {
                show.let { viewModel.actionFlow.emit(ShowDetailAction.Favorite(it)) }
            }
        }
        show()
    }

    private fun setDescription(description: String?) {
        binding.description.text = description
        binding.description.setOnClickListener { binding.description.toggle() }
    }

    private fun setCast(characters: List<CharacterModel>?) {
        binding.characters.removeAllViews()
        characters?.forEach { character ->
            val inflater = LayoutInflater.from(requireContext())
            val view = inflater.inflate(R.layout.item_character, binding.characters, false)
            val image = view.findViewById(R.id.profile) as ImageView
            val name = view.findViewById(R.id.name) as TextView
            name.text = character.name
            image.load(character.image) {
                transformations(CircleCropTransformation())
                placeholder(R.drawable.ic_downloading)
                error(R.drawable.ic_cast_placeholder)
            }
            view.setOnClickListener { openCharacter(character) }
            binding.characters.addView(view)
        }
    }

    private fun openCharacter(character: CharacterModel) {
        val intent = Intent(requireContext(), CharacterActivity::class.java)
        intent.putExtra(ID_EXTRA, character)
        startActivity(intent)
    }

    private fun openEpisode(episode: EpisodeModel) {
        findNavController().navigate(
            NavGraphDirections.appToEpisode(episode)
        )
    }

    private fun setSeasons(seasons: Map<String, List<EpisodeModel>>?) = seasons?.run {
        val expandableListTitle = ArrayList(seasons.keys)
        val expandableListAdapter = SeasonAdapter(requireContext(), expandableListTitle, seasons)
        binding.seasons.setAdapter(expandableListAdapter)

        binding.seasons.setOnChildClickListener { _, _, groupPosition, childPosition, _ ->
            val episode = seasons[expandableListTitle[groupPosition]]?.get(childPosition)
            episode?.let { openEpisode(it) }
            false
        }
    }
}

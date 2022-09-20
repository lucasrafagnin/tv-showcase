package com.rafagnin.tvshowcase.presentation.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.rafagnin.tvshowcase.R
import com.rafagnin.tvshowcase.databinding.FragmentEpisodeDetailBinding
import com.rafagnin.tvshowcase.domain.model.EpisodeModel
import com.rafagnin.tvshowcase.ext.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeFragment : Fragment() {

    private val args: EpisodeFragmentArgs by navArgs()
    private lateinit var binding: FragmentEpisodeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentEpisodeDetailBinding.inflate(inflater, container, false)
        this.binding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView(args.episode)
    }

    private fun setupView(episode: EpisodeModel?) = episode?.let {
        binding.banner.load(episode.image ?: episode.show?.image)
        binding.name.text = episode.name
        binding.date.text = episode.airdate
        binding.season.text = getString(R.string.schedule_season_prefix, episode.season)
        binding.episode.text = getString(R.string.schedule_episode_prefix, episode.episode)
        binding.description.text = episode.description
        episode.site?.let { site ->
            binding.openSite.show()
            binding.openSite.setOnClickListener { openSite(site) }
        }
        episode.runtime?.let {
            binding.duration.text = getString(R.string.common_minutes, it)
            binding.duration.show()
        }
    }

    private fun openSite(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }
}

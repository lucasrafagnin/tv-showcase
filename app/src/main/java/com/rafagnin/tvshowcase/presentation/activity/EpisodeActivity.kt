package com.rafagnin.tvshowcase.presentation.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.rafagnin.tvshowcase.R
import com.rafagnin.tvshowcase.databinding.ActivityEpisodeDetailBinding
import com.rafagnin.tvshowcase.domain.model.EpisodeModel
import com.rafagnin.tvshowcase.ext.show
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EpisodeActivity : AppCompatActivity() {

    companion object {
        const val ID_EXTRA = "ID_EXTRA"
    }

    private lateinit var binding: ActivityEpisodeDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEpisodeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val episodeModel = intent.getSerializableExtra(ID_EXTRA) as EpisodeModel
        setupView(episodeModel)
        setToolbar()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupView(episode: EpisodeModel?) = episode?.let {
        binding.toolbar.title = episode.show?.name
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

    private fun setToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }
}

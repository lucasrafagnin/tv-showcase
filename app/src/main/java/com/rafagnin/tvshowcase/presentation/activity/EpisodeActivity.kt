package com.rafagnin.tvshowcase.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.rafagnin.tvshowcase.databinding.ActivityEpisodeDetailBinding
import com.rafagnin.tvshowcase.domain.model.EpisodeModel
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
        binding.duration.text = episode.runtime.toString()
        binding.episode.text = episode.episode
        binding.season.text = episode.season
        binding.description.text = episode.description
    }

    private fun setToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }
}

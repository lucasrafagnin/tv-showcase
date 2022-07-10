package com.rafagnin.tvshowcase.presentation.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.rafagnin.tvshowcase.databinding.ActivityCharacterDetailBinding
import com.rafagnin.tvshowcase.domain.model.CharacterModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterActivity : AppCompatActivity() {

    companion object {
        const val ID_EXTRA = "ID_EXTRA"
    }

    private lateinit var binding: ActivityCharacterDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val model = intent.getSerializableExtra(ID_EXTRA) as CharacterModel
        setupView(model)
        setToolbar()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupView(model: CharacterModel?) = model?.let {
        binding.toolbar.title = model.name
        binding.poster.load(model.image)
        binding.birtday.text = model.birthday
        binding.country.text = model.country
        binding.gender.text = model.gender
        model.site?.let { site ->
            binding.openSite.show()
            binding.openSite.setOnClickListener { openSite(site) }
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

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
import com.rafagnin.tvshowcase.databinding.FragmentCharacterDetailBinding
import com.rafagnin.tvshowcase.domain.model.CharacterModel
import com.rafagnin.tvshowcase.presentation.activity.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFragment : Fragment() {

    private val args: CharacterFragmentArgs by navArgs()
    private lateinit var binding: FragmentCharacterDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        this.binding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(args.character)
    }

    private fun setupView(model: CharacterModel?) = model?.let {
        (requireActivity() as MainActivity).supportActionBar?.title = model.name
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
}

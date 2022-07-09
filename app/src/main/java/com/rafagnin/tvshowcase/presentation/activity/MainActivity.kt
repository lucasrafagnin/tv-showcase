package com.rafagnin.tvshowcase.presentation.activity

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.rafagnin.tvshowcase.R
import com.rafagnin.tvshowcase.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        setNavController()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    private fun setNavController() {
        val navHost = (supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment)
        val bottomNavigation = binding.bottomNavigation

        bottomNavigation.setupWithNavController(navHost.navController)
    }
}

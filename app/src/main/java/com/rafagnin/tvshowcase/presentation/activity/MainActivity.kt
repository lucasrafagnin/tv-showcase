package com.rafagnin.tvshowcase.presentation.activity

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.rafagnin.tvshowcase.R
import com.rafagnin.tvshowcase.databinding.ActivityMainBinding
import com.rafagnin.tvshowcase.presentation.fragment.ProfileFragment
import com.rafagnin.tvshowcase.presentation.fragment.DiscoveryFragment
import com.rafagnin.tvshowcase.presentation.fragment.ScheduleFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setNavController()
        checkShortcut()
    }

    override fun onBackPressed() = with(binding.bottomNavigation) {
        if (selectedItemId == R.id.discovery_fragment) finish()
        else selectedItemId = R.id.discovery_fragment
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).setSearchableInfo(
            searchManager.getSearchableInfo(
                ComponentName(
                    this,
                    SearchActivity::class.java
                )
            )
        )

        return super.onCreateOptionsMenu(menu)
    }

    private fun setNavController() {
        val navHost = (supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.schedule_fragment,
                R.id.discovery_fragment,
                R.id.profile_fragment,
            )
        )

        binding.toolbar.setupWithNavController(navHost.navController, appBarConfiguration)
        binding.bottomNavigation.setupWithNavController(navHost.navController)
        setSupportActionBar(binding.toolbar)
    }

    private fun checkShortcut() = when (intent?.action) {
        DiscoveryFragment.SHORTCUT -> R.id.discovery_fragment
        ScheduleFragment.SHORTCUT -> R.id.schedule_fragment
        ProfileFragment.SHORTCUT -> R.id.profile_fragment
        else -> null
    }?.also {
        binding.bottomNavigation.selectedItemId = it
    }
}

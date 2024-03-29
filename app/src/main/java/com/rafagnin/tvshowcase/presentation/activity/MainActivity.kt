package com.rafagnin.tvshowcase.presentation.activity

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.rafagnin.tvshowcase.NavGraphDirections
import com.rafagnin.tvshowcase.R
import com.rafagnin.tvshowcase.databinding.ActivityMainBinding
import com.rafagnin.tvshowcase.presentation.fragment.FavoritesFragment
import com.rafagnin.tvshowcase.presentation.fragment.HomeFragment
import com.rafagnin.tvshowcase.presentation.fragment.ScheduleFragment
import com.rafagnin.tvshowcase.presentation.fragment.ShowDetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setNavController()
        checkShortcut()
    }

    override fun onBackPressed() {
        with(binding.bottomNavigation) {
            if (isVisible) {
                if (selectedItemId == R.id.home_fragment) finish()
                else selectedItemId = R.id.home_fragment
            } else {
                onSupportNavigateUp()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navHost = (supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment)
        return navHost.navController.navigateUp() || super.onSupportNavigateUp()
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
                R.id.home_fragment,
                R.id.schedule_fragment,
                R.id.favorites_fragment
            )
        )

        binding.toolbar.setupWithNavController(navHost.navController, appBarConfiguration)
        binding.bottomNavigation.setupWithNavController(navHost.navController)
        navHost.navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNavigation.isVisible =
                appBarConfiguration.topLevelDestinations.contains(destination.id)
        }
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navHost.navController, appBarConfiguration)
    }

    private fun checkShortcut() = with(intent?.action) {
        when (this) {
            HomeFragment.SHORTCUT -> R.id.home_fragment
            ScheduleFragment.SHORTCUT -> R.id.schedule_fragment
            FavoritesFragment.SHORTCUT -> R.id.favorites_fragment
            else -> null
        }?.also {
            binding.bottomNavigation.selectedItemId = it
        } ?: run {
            if (this == ShowDetailFragment.SHORTCUT) {
                openShowDetail(intent.getLongExtra(ShowDetailFragment.SHOW_ID, 0L))
            }
        }
    }

    private fun openShowDetail(id: Long) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(
            NavGraphDirections.appToShowdetail(id)
        )
    }
}

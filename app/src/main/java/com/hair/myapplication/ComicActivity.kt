package com.hair.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import com.google.android.material.tabs.TabLayoutMediator
import com.hair.myapplication.adapter.ComicPagerAdapter
import com.hair.myapplication.databinding.ActivityComicBinding
import com.hair.myapplication.network.ComicClient
import com.hair.myapplication.vm.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ComicActivity : AppCompatActivity() {
    private val vm: MainViewModel by viewModels()
    private lateinit var binding: ActivityComicBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComicBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_hostFragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.searchFragment, R.id.favoriteFragment),
            binding.drawer
        )
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController,appBarConfiguration)
        binding.bottomNav.setupWithNavController(navController)
        binding.navPage.setupWithNavController(navController)


    }

//    private fun setupNavController() {
//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.nav_hostFragment) as NavHostFragment
//        navController = navHostFragment.findNavController()
////        setupActionBarWithNavController(navController)
//        NavigationUI.setupWithNavController(binding.navPage, navController)
//    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
//    override fun onSupportNavigateUp(): Boolean {
//        return NavigationUI.navigateUp(
//            navController,
//            binding.drawer
//        )
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_nav,menu)
        return true
    }
    override fun onBackPressed() {
        if (binding.drawer.isDrawerOpen(GravityCompat.END)) {
            binding.drawer.closeDrawer(GravityCompat.END)
        } else {
            super.onBackPressed()
        }
    }
}
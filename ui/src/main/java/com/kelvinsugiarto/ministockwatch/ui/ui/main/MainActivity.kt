package com.kelvinsugiarto.ministockwatch.ui.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.kelvinsugiarto.ministockwatch.ui.databinding.ActivityMainBinding
import com.kelvinsugiarto.ministockwatch.ui.R




class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var bottomNavigationView: BottomNavigationView
//    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.drawer_menu, menu)
        return true
    }

    fun setToolbarTitle(title:String){
        binding.toolbarTitle.text = title
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.main_nav_host) //Initialising navController

        setSupportActionBar(binding.mainToolbar) //Set toolbar
        supportActionBar!!.title = ""

        setupNavControl()
    }


    private fun setupNavControl() {
        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.watchListFragment,
                R.id.streamViewFragment,
                R.id.loginFragment
            ),binding.mainDrawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
    }


//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return item.onNavDestinationSelected(navController) ||
//                super.onOptionsItemSelected(item)
//    }

//    override fun onSupportNavigateUp(): Boolean {
//        return NavigationUI.navigateUp(navController,appBarConfiguration)
//    }



    fun hideBottomNavigation() { //Hide bottom navigation
        binding.mainBottomNavigationView.visibility = View.GONE
        binding.mainNavigationView.visibility = View.VISIBLE
        binding.mainDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED) //To unlock navigation drawer

        binding.mainNavigationView.setupWithNavController(navController) //Setup Drawer navigation with navController
    }

    override fun onBackPressed() {
        val currentDestId = navController.currentDestination?.id
        if (currentDestId == R.id.watchListFragment || currentDestId == R.id.loginFragment) {
            finish()
        } else {
            super.onBackPressed()
        }
    }
}
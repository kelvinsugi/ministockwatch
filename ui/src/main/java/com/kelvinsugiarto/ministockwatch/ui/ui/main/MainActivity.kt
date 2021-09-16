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
import com.kelvinsugiarto.ministockwatch.ui.ui.login.LoginResult
import com.kelvinsugiarto.ministockwatch.ui.ui.login.LoginViewModel
import com.kelvinsugiarto.ministockwatch.ui.utils.setGone
import com.kelvinsugiarto.ministockwatch.ui.utils.setVisible
import com.kelvinsugiarto.ministockwatch.ui.utils.showSuccessSnackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var bottomNavigationView: BottomNavigationView
//    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    var isLoggedIn : Boolean = false
    private val viewModel by viewModel<MainActivityViewModel>()
    private val loginViewModel by viewModel<LoginViewModel>()

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
        setObserver()
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        menu?.findItem(R.id.sign_out)?.setVisible(isLoggedIn)
        menu?.findItem(R.id.action_support)?.setVisible(!isLoggedIn)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sign_out -> {
                loginViewModel.doLogout()
                navController.navigate(R.id.loginFragment, null)
            }
        }
        return super.onOptionsItemSelected(item)
    }

//    override fun onSupportNavigateUp(): Boolean {
//        return NavigationUI.navigateUp(navController,appBarConfiguration)
//    }

    fun showBottomNavigation(){
        binding.mainBottomNavigationView.visibility = View.VISIBLE

        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.watchListFragment,
                R.id.streamViewFragment,
                R.id.loginFragment
            )
        )

        NavigationUI.setupWithNavController(
            binding.mainBottomNavigationView,
            navController
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
    }


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

    private fun setObserver(){
        loginViewModel.loginStateObservable.observe(this) {
            if(it == true){
                isLoggedIn = true
                invalidateOptionsMenu()
            }else{
                isLoggedIn = false
                invalidateOptionsMenu()
            }
        }
    }
}
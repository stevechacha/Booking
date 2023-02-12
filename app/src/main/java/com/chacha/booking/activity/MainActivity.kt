package com.chacha.booking.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import com.chacha.booking.R
import com.chacha.booking.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

      /*  setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)*/

        setupBottomNavigation()


    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.apply {
            val navView: BottomNavigationView = findViewById(R.id.bottomNavigation)
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
            navHostFragment.navController.apply {
                setupWithNavController(this,)
                addOnDestinationChangedListener { _, _, args ->
                    // Top level items should have such argument with value set to true
                    isVisible = args?.getBoolean("hasBottomNav", false) == true
                }
                setOnItemReselectedListener {
                    when(it.itemId){
                        R.id.bookingFragment ->{
                        }
                        R.id.tripsFragment ->{
                        }
                        R.id.parcelFragment ->{
                        }
                        R.id.profileFragment ->{
                        }

                    }
                    return@setOnItemReselectedListener

                    // Do nothing when selecting same item
                }
                // Set the bottom navigation view/bar background color

            }
        }
    }


  /*  override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }*/
}
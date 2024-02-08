package com.example.islamic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.islamic.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setLocalization()
        disableDarkMode()
        connectNavControllerWithNavHostFragment()
        setUpBottomNavWithNavController()
        bottomBarAppearance()
    }



    private fun bottomBarAppearance() {
        val allowedDestinations = setOf(
            R.id.quranFragment,
            R.id.hadethFragment,
            R.id.sebhaFragment,
            R.id.radioFragment,

        )
        navController.addOnDestinationChangedListener { _, destination, _ ->
            showBottomNavigationView(destination.id in allowedDestinations)
        }
    }

    private fun showBottomNavigationView(show: Boolean) {
        binding.bottomNavigationView.visibility = if (show) View.VISIBLE else View.INVISIBLE
    }

    private fun setUpBottomNavWithNavController() {
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    private fun connectNavControllerWithNavHostFragment() {
        // Initialize the NavController and connect it with the NavHostFragment
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
    }


    private fun setLocalization() {
        val locale = Locale("ar")
        Locale.setDefault(locale)
        val config = baseContext.resources.configuration
        config.setLocale(locale)
        baseContext.createConfigurationContext(config)
    }

    private fun disableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}
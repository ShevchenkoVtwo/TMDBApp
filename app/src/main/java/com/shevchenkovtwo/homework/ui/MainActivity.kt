package com.shevchenkovtwo.homework.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.shevchenkovtwo.homework.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.ui.setupWithNavController


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mainActivityBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val bottomNavigationView = mainActivityBinding.BottomNavigationView
        val navHostFragment =
            supportFragmentManager.findFragmentById(mainActivityBinding.navHostFragment.id) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)
    }

}
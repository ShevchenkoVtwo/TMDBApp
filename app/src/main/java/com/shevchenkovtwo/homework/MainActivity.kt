package com.shevchenkovtwo.homework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.shevchenkovtwo.homework.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(mainActivityBinding.container.id) as NavHostFragment
        val navController = navHostFragment.navController
    }

}
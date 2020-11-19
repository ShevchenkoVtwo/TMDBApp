package com.shevchenkovtwo.homework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shevchenkovtwo.homework.databinding.ActivityMovieDetailBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
package com.shevchenkovtwo.homework

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shevchenkovtwo.homework.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val string: String? = intent.getStringExtra(AppConstants.stringObject)
        val int: Int = intent.getIntExtra(AppConstants.integerObject, 0)

        binding.tvStringMain.text = "Values were passed from previous screen:$string"
        binding.tvIntegerMain.text = "Values were passed from previous screen:$int"
        binding.buttonToSecond
            .setOnClickListener { dataToSecondActivity() }
    }

    private fun dataToSecondActivity() {
        val intent = Intent(this, SecondActivity::class.java)
        val stringToPass = "Text from MainActivity"
        val integerToPass = 123
        intent.putExtra(AppConstants.stringObject, stringToPass)
        intent.putExtra(AppConstants.integerObject, integerToPass)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}
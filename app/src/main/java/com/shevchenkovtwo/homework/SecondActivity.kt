package com.shevchenkovtwo.homework

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat
import com.shevchenkovtwo.homework.databinding.ActivitySecondBinding


class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val string: String? = intent.getStringExtra(AppConstants.stringObject)
        val int: Int = intent.getIntExtra(AppConstants.integerObject, 0)

        val textViewString = binding.tvString
        textViewString.text = "Values were passed from previous screen:$string"

        val textViewInteger = binding.tvInteger
        textViewInteger.text = "Values were passed from previous screen:$int"

        val button = binding.buttonToMain.
            setOnClickListener { dataToMainActivity() }
    }

    private fun dataToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        val stringToPass = "Text from SecondActivity"
        val integerToPass = 321
        intent.putExtra(AppConstants.stringObject, stringToPass)
        intent.putExtra(AppConstants.integerObject, integerToPass)
        val options: ActivityOptionsCompat = ActivityOptionsCompat
            .makeSceneTransitionAnimation(this, binding.imvShared, "Image")
        startActivity(intent,options.toBundle())
    }
}
package com.andriod.pushmessages

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.andriod.pushmessages.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureScrollView()
    }

    private fun configureScrollView() {
        DataManager.message.observe(this){
            binding.scrollView.addView(
                TextView(this).apply { text = it }
            )
        }
    }
}
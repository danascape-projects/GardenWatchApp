package dev.danascape.gardenwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.danascape.gardenwatch.databinding.ActivityMainBinding
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(R.style.Theme_GardenWatch)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
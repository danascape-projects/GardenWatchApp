package dev.danascape.gardenwatch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.danascape.gardenwatch.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(R.style.Base_Theme_GardenWatch)
        setContentView(R.layout.activity_home)
    }
}
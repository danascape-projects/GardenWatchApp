package dev.danascape.gardenwatch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dev.danascape.gardenwatch.R
import dev.danascape.gardenwatch.util.NetworkUtils.isOnline

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(R.style.Base_Theme_GardenWatch)
        setContentView(R.layout.activity_home)

        if (!isOnline(this)) {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Internet Connection Found", Toast.LENGTH_SHORT).show()
        }
    }
}
package dev.danascape.gardenwatch.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dev.danascape.gardenwatch.databinding.ActivityMainBinding
import dev.danascape.gardenwatch.models.PlantModel
import dev.danascape.gardenwatch.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        mainViewModel = MainViewModel()
        subscribe()

        mainViewModel.getPlantData()

        binding.swipeRefresh.setOnRefreshListener {
            Toast.makeText(this, "Refreshing..", Toast.LENGTH_SHORT).show()
            mainViewModel.getPlantData()
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun subscribe() {
        mainViewModel.isLoading.observe(this) { isLoading ->
            // Is sending the API request
            Toast.makeText(this, "Sending Request", Toast.LENGTH_SHORT).show()
        }

        mainViewModel.isError.observe(this) { isError ->
            // Encountered an error in the process
            if (isError) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }

        mainViewModel.platData.observe(this) { plantData ->
            Toast.makeText(this, "Got result", Toast.LENGTH_SHORT).show()
            setResultText(plantData)
        }
    }

    private fun setResultText(plantData: PlantModel) {
        binding.tvHumidity.text = plantData.humidity
        binding.tvTemperature.text = plantData.temperature
        binding.tvSoil.text = plantData.soilmoisture
        binding.tvAQI.text = plantData.airquality
    }
}
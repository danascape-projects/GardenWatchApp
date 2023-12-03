package dev.danascape.gardenwatch.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import dev.danascape.gardenwatch.R
import dev.danascape.gardenwatch.api.GithubAPIClient
import dev.danascape.gardenwatch.api.HomeInterface
import dev.danascape.gardenwatch.databinding.ActivityHomeBinding
import dev.danascape.gardenwatch.models.PlantModel
import dev.danascape.gardenwatch.util.NetworkUtils.isOnline
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    private var mApiService: HomeInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)

        mApiService = GithubAPIClient.client.create(HomeInterface::class.java)
        fetchUpdate()

        binding.btnRefresh.setOnClickListener {
            Toast.makeText(this, "Refreshing..", Toast.LENGTH_SHORT).show()
            fetchUpdate()
        }

        binding.swipeRefresh.setOnRefreshListener {
            Toast.makeText(this, "Refreshing..", Toast.LENGTH_SHORT).show()
            fetchUpdate()
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun fetchUpdate() {
        val retrofit = mApiService!!.getBuildInfo()
        retrofit.enqueue(object: Callback<PlantModel> {

            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<PlantModel>, response: Response<PlantModel>) {
                val resBody = response.body()
                if(resBody != null){
                    binding.tvHumidity.text = "Humidity: ${resBody.humidity}%"
                    binding.tvTemperature.text = "Temperature: ${resBody.temperature} C"
                    binding.tvSoil.text = "Soil Moisture: ${resBody.soilmoisture}%"
                    binding.tvAQI.text = "CO2 Level: ${resBody.airquality} ppm"
                }
            }

            override fun onFailure(call: Call<PlantModel>, t: Throwable) {
                Log.e("retrofitResponse","Error: ${t.message}")
            }
        })
    }
}
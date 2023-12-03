package dev.danascape.gardenwatch.api

import dev.danascape.gardenwatch.models.PlantModel
import retrofit2.Call
import retrofit2.http.GET

interface HomeInterface {
    @GET("plant.json")
    fun getBuildInfo(): Call<PlantModel>
}
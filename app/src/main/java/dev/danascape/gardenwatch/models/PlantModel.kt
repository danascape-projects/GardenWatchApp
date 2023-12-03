package dev.danascape.gardenwatch.models

import com.google.gson.annotations.SerializedName

data class PlantModel(
    @SerializedName("humidity")
    val humidity: String,
    val temperature: String,
    val soilmoisture: String,
    val airquality: String
)

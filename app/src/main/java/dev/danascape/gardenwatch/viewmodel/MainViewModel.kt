package dev.danascape.gardenwatch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.danascape.gardenwatch.api.ApiConfig
import dev.danascape.gardenwatch.models.PlantModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel() : ViewModel() {

    private val _plantData = MutableLiveData<PlantModel>()
    val platData: LiveData<PlantModel> get() = _plantData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> get() = _isError

    var errorMessage: String = ""
        private set

    fun getPlantData() {
        _isLoading.value = true
        _isError.value = false

        val client = ApiConfig.getApiService().getPlantInfo()

        client.enqueue(object : Callback<PlantModel> {
            override fun onResponse(call: Call<PlantModel>, response: Response<PlantModel>) {
                val responseBody = response.body()
                if (!response.isSuccessful || responseBody == null) {
                    onError("Data Processing Error")
                    return
                }

                _isLoading.value = false
                _plantData.postValue(responseBody!!)
            }

            override fun onFailure(call: Call<PlantModel>, t: Throwable) {
                onError(t.message)
                t.printStackTrace()
            }

        })
    }

    private fun onError(inputMessage: String?) {

        val message = if (inputMessage.isNullOrBlank() or inputMessage.isNullOrEmpty()) "Unknown Error"
        else inputMessage

        errorMessage = StringBuilder("ERROR: ")
            .append("$message some data may not displayed properly").toString()

        _isError.value = true
        _isLoading.value = false
    }
}
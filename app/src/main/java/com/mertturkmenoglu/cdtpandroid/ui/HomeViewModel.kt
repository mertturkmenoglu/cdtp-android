package com.mertturkmenoglu.cdtpandroid.ui

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertturkmenoglu.cdtpandroid.data.model.Greenhouse
import com.mertturkmenoglu.cdtpandroid.data.model.GreenhouseUpdateBody
import com.mertturkmenoglu.cdtpandroid.util.GreenhouseApiAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error

class HomeViewModel : ViewModel(), AnkoLogger {
    private var mGreenhouses = MutableLiveData<List<Greenhouse>>()

    fun getGreenhouses(): LiveData<List<Greenhouse>> {
        viewModelScope.launch(Dispatchers.IO) {
            getGreenhouseData()
        }

        return mGreenhouses
    }

    fun updateGreenhouse(name: String, newTemperature: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            updateGreenhouseData(name, newTemperature)
        }
    }

    private suspend fun getGreenhouseData() {
        try {
            val response = GreenhouseApiAdapter.client.getAllGreenhouses()

            if (response.isSuccessful && response.body() != null) {
                val data = response.body()
                mGreenhouses.postValue(data?.greenhouses)
            } else {
                error { "Cannot fetch greenhouses" }
            }
        } catch (e: Exception) {
            error { "GetGreenhouseData failed: ${e.message}" }
        }
    }

    private suspend fun updateGreenhouseData(name: String, newTemperature: Int) {
        try {
            GreenhouseApiAdapter.client.updateGreenhouse(
                name,
                GreenhouseUpdateBody(newTemperature)
            )
        } catch (e: Exception) {
            error { "UpdateGreenhouse failed: ${e.message}" }
        }
    }
}
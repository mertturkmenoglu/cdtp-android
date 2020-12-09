package com.mertturkmenoglu.cdtpandroid.util

import com.mertturkmenoglu.cdtpandroid.services.GreenhouseApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GreenhouseApiAdapter {
    private const val apiBaseUrl = "https://cdtp-backend.herokuapp.com"

    val client: GreenhouseApiService = Retrofit.Builder()
        .baseUrl(apiBaseUrl)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GreenhouseApiService::class.java)
}
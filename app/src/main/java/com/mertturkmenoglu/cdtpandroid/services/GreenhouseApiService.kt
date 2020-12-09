package com.mertturkmenoglu.cdtpandroid.services

import com.mertturkmenoglu.cdtpandroid.data.model.GreenhouseAllResponse
import com.mertturkmenoglu.cdtpandroid.data.model.GreenhouseUpdateBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface GreenhouseApiService {

    @GET("/api/all")
    suspend fun getAllGreenhouses(): Response<GreenhouseAllResponse>

    @PUT("/api/{name}")
    suspend fun updateGreenhouse(@Path("name") name: String, @Body body: GreenhouseUpdateBody)
}
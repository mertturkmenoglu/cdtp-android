package com.mertturkmenoglu.cdtpandroid.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class GreenhouseUpdateBody(@SerializedName("temperature_wish") val temperatureWish: Int)
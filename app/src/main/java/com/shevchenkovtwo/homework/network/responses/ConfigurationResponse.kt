package com.shevchenkovtwo.homework.network.responses

import com.shevchenkovtwo.homework.data.models.Configuration
import com.squareup.moshi.Json


data class ConfigurationResponse(
    @Json(name = "images")
    val configuration: Configuration
)
package com.shevchenkovtwo.tmdbApp.domain.network.responses

import com.shevchenkovtwo.tmdbApp.domain.network.networkmodels.ConfigurationDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ConfigurationResponse(
    @Json(name = "images")
    val images: ConfigurationDto,
)

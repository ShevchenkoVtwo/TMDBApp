package com.shevchenkovtwo.homework.network.responses

import com.shevchenkovtwo.homework.network.models.ConfigurationDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ConfigurationResponse(
    @Json(name = "images")
    val images: ConfigurationDto,
)
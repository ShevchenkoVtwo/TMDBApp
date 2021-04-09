package com.shevchenkovtwo.homework.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ConfigurationDto(
    @Json(name = "secure_base_url")
    val imagesSecureUrl: String,
)
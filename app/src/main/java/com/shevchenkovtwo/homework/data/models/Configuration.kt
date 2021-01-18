package com.shevchenkovtwo.homework.data.models

import com.squareup.moshi.Json


data class Configuration(
    @Json(name = "secure_base_url")
    val imagesSecureUrl: String,
)
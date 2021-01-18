package com.shevchenkovtwo.homework.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Actor(
    val id: Int,
    val name: String,
    @Json(name = "profile_path")
    val picture: String?
)
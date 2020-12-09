package com.shevchenkovtwo.homework

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Movie(
    @DrawableRes
    val imageMask: Int? = null,
    @DrawableRes
    val imageForList: Int,
    val favorite: Boolean,
    val name: String,
    val tags: String,
    val duration: String,
    val rating: Float,
    val reviews: String,
    val pg: String,
    @StringRes
    val storyline: Int? = null,
    val actors: List<Actor>? = null
)
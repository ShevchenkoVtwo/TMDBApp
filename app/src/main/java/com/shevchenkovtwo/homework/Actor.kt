package com.shevchenkovtwo.homework

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Actor(
    @DrawableRes
    val image: Int,
    @StringRes
    val name: Int)

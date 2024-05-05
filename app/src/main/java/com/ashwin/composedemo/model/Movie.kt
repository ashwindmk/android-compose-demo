package com.ashwin.composedemo.model

import androidx.annotation.DrawableRes

data class Movie(
    val title: String,
    @DrawableRes val image: Int,
    val description: String
)

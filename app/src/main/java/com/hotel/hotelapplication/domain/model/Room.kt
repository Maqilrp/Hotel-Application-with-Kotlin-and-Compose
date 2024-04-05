package com.hotel.hotelapplication.domain.model

import androidx.annotation.DrawableRes

// isi data dari room
data class Room(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    @DrawableRes val image: Int
)

package com.hotel.hotelapplication.navigation

//import android.health.connect.datatypes.ExerciseRoute

sealed class Screen(val  route: String) {
    data object Home: Screen("homeScreen")
    data object About: Screen("aboutScreen")
    data object Reservation: Screen("reservationScreen")
    data object Receipt: Screen("receiptScreen")
}
package com.hotel.hotelapplication.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hotel.hotelapplication.presentation.screen.AboutScreen
import com.hotel.hotelapplication.presentation.screen.HomeScreen
import com.hotel.hotelapplication.presentation.screen.ReceiptScreen
import com.hotel.hotelapplication.presentation.screen.ReservationScreen


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun  SetupNavGraph(navController: NavHostController = rememberNavController()){
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(route = Screen.Home.route){
            HomeScreen(navController)
        }
        composable(route = Screen.About.route){
            AboutScreen(navController)
        }
        composable(route = Screen.Reservation.route){
            ReservationScreen(navController)
        }
        composable(route = Screen.Receipt.route){
            ReceiptScreen(navController)
        }
    }
}
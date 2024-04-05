package com.hotel.hotelapplication.presentation.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.hotel.hotelapplication.R
import com.hotel.hotelapplication.domain.model.Room
import com.hotel.hotelapplication.navigation.Screen
import com.hotel.hotelapplication.presentation.util.component.RoomCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.About.route)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(R.string.about_application),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ) { padding ->
        ScreenContent(navController,Modifier.padding(padding))
    }
}

//fun getRoom(id:Long,title:String,price:Double,description:String,category: String,image: Int) {
//
//}

//data class RoomDetailArgs(val roomId: Int, val roomTitle: String, val roomPrice: Double, val roomDesc: String, val roomCategory: String, @DrawableRes val roomImage: Int)
@Composable
fun ScreenContent(navController: NavHostController, modifier: Modifier) {
    val rooms = listOf(
        Room(1, "Room 1", 100.0, "This is room 1", "Standard", R.drawable.modern_studio_apartment_design_with_bedroom_living_space),
        Room(2, "Room 2", 120.0, "This is room 2", "Deluxe", R.drawable.modern_studio_apartment_design_with_bedroom_living_space),
        Room(3, "Room 3", 150.0, "This is room 3", "Suite", R.drawable.modern_studio_apartment_design_with_bedroom_living_space),
        Room(4, "Room 4", 180.0, "This is room 4", "Standard", R.drawable.modern_studio_apartment_design_with_bedroom_living_space),
        Room(5, "Room 5", 200.0, "This is room 5", "Deluxe", R.drawable.modern_studio_apartment_design_with_bedroom_living_space)
    )

    LazyColumn(modifier = modifier) {
        items(rooms.size) { index ->
            RoomCard(
                onClick = {
                    navController.navigate(Screen.Reservation.route)},
                room = rooms[index],
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

// cara pass data ???
//fun getData(): List<Room>{
//    return  listOf(
//        Room(1, "Room 1", 100.0, "This is room 1", "Standard", R.drawable.modern_studio_apartment_design_with_bedroom_living_space),
//        Room(2, "Room 2", 120.0, "This is room 2", "Deluxe", R.drawable.modern_studio_apartment_design_with_bedroom_living_space),
//        Room(3, "Room 3", 150.0, "This is room 3", "Suite", R.drawable.modern_studio_apartment_design_with_bedroom_living_space),
//        Room(4, "Room 4", 180.0, "This is room 4", "Standard", R.drawable.modern_studio_apartment_design_with_bedroom_living_space),
//        Room(5, "Room 5", 200.0, "This is room 5", "Deluxe", R.drawable.modern_studio_apartment_design_with_bedroom_living_space)
//
//    )
//}
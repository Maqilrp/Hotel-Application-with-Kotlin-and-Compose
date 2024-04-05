package com.hotel.hotelapplication.presentation.util.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hotel.hotelapplication.R
import com.hotel.hotelapplication.domain.model.Room

@Composable
fun RoomCard(
    onClick: () -> Unit,
    room: Room,
    modifier: Modifier
){
    val paddingValue = 16.dp
    Card(
//        untuk menambahkan efek shadow
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
//        untuk ukuran card
        modifier = modifier
            .padding(100.dp)
            .fillMaxWidth()
            .clickable { onClick() }
    ){
        Column (
            modifier = Modifier
                .padding(paddingValue)
                .fillMaxWidth()
        ) {
            Image(
                modifier = Modifier.fillMaxWidth().height(200.dp),
                painter = painterResource(id = room.image), contentDescription = room.title,
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.padding(paddingValue))

            Column {
                Text(text = room.title)
                Text(text = room.description)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RoomCardPreview() {
    val room = Room(1, "Sample Room", 100.0, "This is a sample room", "Standard", R.drawable.modern_studio_apartment_design_with_bedroom_living_space)
    RoomCard(onClick = {}, room, Modifier.padding(vertical = 8.dp))
}
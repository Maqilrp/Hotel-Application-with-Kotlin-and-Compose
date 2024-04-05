package com.hotel.hotelapplication.presentation.screen

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.hotel.hotelapplication.R
import com.hotel.hotelapplication.navigation.Screen
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservationScreen(navController: NavHostController){

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack()}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
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
        ScreenContent(Modifier.padding(padding))
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenContent(modifier: Modifier) {
    var name by rememberSaveable { mutableStateOf("") }
    var nameError by rememberSaveable { mutableStateOf(false) }

    val selectedDateState = rememberSaveable { mutableStateOf("") }
    val checkIn = rememberSheetState()
    CalendarDialog(
        state = checkIn,
        config = CalendarConfig(
            monthSelection = true,
            yearSelection = true,
            style = CalendarStyle.MONTH,
            disabledDates = listOf(LocalDate.now().plusDays(7))
        ),
        selection = CalendarSelection.Date { date ->
            selectedDateState.value = date.toString()
            Log.d("SelectedDate", "$date")
        }
    )
    var checkInError by rememberSaveable { mutableStateOf(false) }

    var people by rememberSaveable { mutableStateOf("") }
    var peopleError by rememberSaveable { mutableStateOf(false) }

    var formattedData by rememberSaveable { mutableStateOf("") }
    val formattedDate = selectedDateState.value

    // State to determine if the Share button should be shown
    var showShareButton by rememberSaveable { mutableStateOf(false) }
    val  context = LocalContext.current

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                nameError = false
            },
            label = { Text(stringResource(R.string.name)) },
            isError = nameError,
            trailingIcon = { IconPicker(nameError) },
            supportingText = { ErrorHint(nameError) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        // People textfield
        OutlinedTextField(
            value = people,
            onValueChange = {
                people = it
                peopleError = false
            },
            label = { Text(stringResource(R.string.numberOfPeople)) },
            isError = peopleError,
            trailingIcon = { IconPicker(peopleError)},
            supportingText = { ErrorHint(peopleError)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        // CheckIn datepicker
        TextField(
            value = selectedDateState.value,
            onValueChange = {
                selectedDateState.value = it
                checkInError = false
            },
            label = { Text(stringResource(R.string.checkin)) },
            isError = checkInError,
            trailingIcon = {
                IconButton(onClick = { checkIn.show() }) {
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = "Select Date"
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                nameError = (name == "" || name == "0")
                peopleError = (people == "" || people == "0")
                checkInError = (false)
                if (nameError || peopleError || checkInError) return@Button
                else {
//                    val formattedDate = selectedDateState.value
                    formattedData = """
                        |Name: ${name.uppercase()}
                        |Number of People: ${people.uppercase()}
                        |Check-In Date: $formattedDate
                    """.trimMargin()
                    // untuk menampilkan share button
                    showShareButton = true
                }
            },
            modifier = Modifier.padding(top = 8.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Text(text = stringResource(id = R.string.submit))
        }

        Text(
            text = formattedData,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.fillMaxWidth()
        )

        // Show the Share button when the state is true
        if (showShareButton) {
            Button(
                onClick = {
                    shareData(context = context, message = context.getString(R.string.bagikan_template,name,people,formattedDate))
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = stringResource(R.string.share))
            }
        }
    }
}


@Composable
fun  IconPicker(isError: Boolean){
    if (isError) {
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null )
    }
}

@Composable
fun ErrorHint(isError: Boolean){
    if (isError) {
        Text(text = stringResource(R.string.input_invalid))
    }
}

private fun  shareData(context: Context, message: String){
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null){
        context.startActivity(shareIntent)
    }
}





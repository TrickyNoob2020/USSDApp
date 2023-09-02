package com.myussdapp.ussdapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myussdapp.ussdapp.ui.theme.UssdAppTheme
import androidx.compose.runtime.remember as remember1




class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UssdAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    HeaderWithButtons()
                }
            }
        }
    }

}


@Composable
fun HeaderWithButtons() {
    @Composable
    fun HeaderWithButtons() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp)) // Move Spacer down

            Text(text = stringResource(text = stringResource(id = R.string.app_name)))
            Spacer(modifier = Modifier.height(20.dp)) // Reduce spacing between header and buttons

            Buttons() // Buttons are now placed closer to the center
        }
    }

    @Composable
fun Buttons() {
    Row(
        modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly // Add spacing between buttons
    ) {
        // Button 1 - Airtime Recharge
        Button(onClick = { clickCount.value++ }) {
            Text(text = stringResource(id = R.string.btn1, clickCount.value))
        }
        // Button 2 - Call Me Request
        Button(onClick = {USSDHandler.handleCallMeRequest("")}) {
            Text(text = stringResource(id = R.string.btn2, clickCount.value))
        }
    }
}

@Preview( showSystemUi = true, showBackground = true)
@Composable
fun DefaultPreview() {
    UssdAppTheme {
       HeaderWithButtons()
    }
}

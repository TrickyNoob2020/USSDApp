package com.myussdapp.ussdapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.myussdapp.ussdapp.ui.theme.UssdAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UssdAppTheme {
                // Set up the navigation graph with NavHost
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "mainFragment",
                    modifier = Modifier.fillMaxSize()
                ) {
                    composable("mainFragment") {
                        RunEverythingInMain(navController)
                    }
                    composable("MTCRoute") {
                        // Content for MTCRoute
                    }
                    composable("TNRoute") {
                        // Content for TNRoute
                    }
                }

            }
        }
    }
}

@Composable
fun RunEverythingInMain(navController: NavController) {
    HeaderWithButtons(navController = navController)
}

@Composable
fun HeaderWithButtons(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 32.dp, horizontal = 20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Text(text = stringResource(id = R.string.app_name))

        Spacer(modifier = Modifier.height(46.dp)) // Add spacing between TextField and buttons
        Buttons(navController)
    }
}

@Composable
fun Buttons(navController: NavController) {
    // I have two buttons "MTC" and "TN",
    // which displayed underneath each-other,
    // each button should be able to open another kotlin file
    Column(
        modifier = Modifier
            .padding(12.dp, 1.dp)
            .fillMaxWidth()
    ) {
        Button(
            onClick = {  navController.navigate("MTCRoute") }
        ) {
            Text(text = "MTC")
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = { /*TODO*/ navController.navigate("TNRoute") }
        ) {
            Text(text = "TN")
        }
    }
}

@Preview
@Composable
private fun Preview() {
    UssdAppTheme {
        HeaderWithButtons(navController = rememberNavController())
    }
}

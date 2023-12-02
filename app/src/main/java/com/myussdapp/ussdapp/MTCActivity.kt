package com.myussdapp.ussdapp

import android.Manifest
import android.os.Bundle
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.PermissionChecker
import androidx.core.content.PermissionChecker.checkSelfPermission
import com.myussdapp.ussdapp.MTC.USSDAwehHandler
import com.myussdapp.ussdapp.mtc.USSDHandler
import com.myussdapp.ussdapp.ui.theme.UssdAppTheme

class MTCActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UssdAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    displayMTCText()
                }
            }
        }
    }
}


@Composable
fun displayMTCText() {
    Column {
        Text(
            text = "MTC Services"
        )
        Divider(modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.primary)
        Text(
            text = "Normal MTC Services"
        )
        NormalMtcButtons()
        Divider(modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.primary)
        Divider(modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.primary, thickness = 12.dp)
        Text(
            text = "Aweh MTC Services"
        )
        Divider(modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.primary)
        AwehMtcButtons()
    }
}



@Composable
fun NormalMtcButtons() {
    val context = LocalContext.current
    var inputValue by remember { mutableStateOf("") }

    // Permission launcher for SEND_SMS
    val smsPermissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                // Permission is granted, call your SMS handling function with the entered value
                USSDHandler.airtimeTransferSMSHandler(context, inputValue)
            } else {
                // Permission is denied, handle accordingly,
                // For example, you can display a message to the user
            }
        }

    Column {
        // Button for Airtime Recharge
        Button(
            onClick = {
                // Show dialog to enter the value
                USSDHandler.handleAirtimeRecharge(context, inputValue)
            },

            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Recharge")
        }

        // Button for Airtime Transfer SMS
        Button(
            onClick = {
                // Check if SEND_SMS permission is granted
                if (checkSelfPermission(context, Manifest.permission.SEND_SMS) == PermissionChecker.PERMISSION_GRANTED) {
                    // Permission is already granted, call your SMS handling function
                    USSDHandler.airtimeTransferSMSHandler(context, inputValue)
                } else {
                    // Permission is not granted, request the permission
                    smsPermissionLauncher.launch(Manifest.permission.SEND_SMS)
                }
            },

            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Transfer AirTime")
        }

        // CallMeRequest
        Button(
            onClick = {
                // Call the handleCallMeRequest function
                USSDHandler.handleCallMeRequest(context, inputValue)
            },

            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Call Me Request")
        }
    }
}

@Composable
fun AwehMtcButtons() {
    val context = LocalContext.current

    Column {
        // Button for okaAweh
        Button(
            onClick = { USSDAwehHandler.okaAweh(context) },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text("okaAweh")
        }

        // Button for awehGo
        Button(
            onClick = { USSDAwehHandler.awehGo(context) },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text("awehGo")
        }

        // Button for awehGig
        Button(
            onClick = { USSDAwehHandler.awehGig(context) },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text("awehGig")
        }

        // Button for awehPrime
        Button(
            onClick = { USSDAwehHandler.awehPrime(context) },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text("awehPrime")
        }

        // Button for superAweh
        Button(
            onClick = { USSDAwehHandler.superAweh(context) },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text("superAweh")
        }

        // Button for awehUltra
        Button(
            onClick = { USSDAwehHandler.awehUltra(context) },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text("awehUltra")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewCode(){
    displayMTCText()
}
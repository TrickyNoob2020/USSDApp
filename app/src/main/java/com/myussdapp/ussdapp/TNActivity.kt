package com.myussdapp.ussdapp

import android.Manifest
import android.os.Bundle
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.PermissionChecker
import com.myussdapp.ussdapp.tn.JivaHandler
import com.myussdapp.ussdapp.tn.TNHandler
import com.myussdapp.ussdapp.ui.theme.UssdAppTheme

class TNActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UssdAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DisplayTNText()
                }
            }
        }
    }
}

@Composable
fun DisplayTNText(){
    Column{
        Text(
            text = "TN Services"
        )
        Divider(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.primary)
        Text(
            text = "Normal TN Services"
        )
        NormalTNButtons()
        Divider(modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.primary)
        Divider(modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.primary, thickness = 12.dp)
        Text(
            text = "Jiva Services"
        )
        Divider(modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.primary)
        JivaButtons()
    }
}

@Composable
fun NormalTNButtons(){
    val context = LocalContext.current
    var inputValue by remember { mutableStateOf("") }

    // Permission launcher for SEND_SMS
    val smsPermissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                // Permission is granted, call your SMS handling function with the entered value
                TNHandler.airtimeTransferHandler(context, inputValue)
            } else {
                // Permission is denied, handle accordingly,
                // For example, you can display a message to the user
            }
        }


    // Button for Airtime Recharge
    Button(
        onClick = {
            TNHandler.rechargeHandler(context, inputValue)
        },
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text("Recharge")
    }

    Spacer(modifier = Modifier.height(16.dp))
    // Transfer Airtime
    Button(
    onClick = {
        // Check if SEND_SMS permission is granted
        if (PermissionChecker.checkSelfPermission(context, Manifest.permission.SEND_SMS) == PermissionChecker.PERMISSION_GRANTED) {
            // Permission is already granted, call your SMS handling function
            TNHandler.airtimeTransferHandler(context, inputValue)
        } else {
            // Permission is not granted, request the permission
            smsPermissionLauncher.launch(Manifest.permission.SEND_SMS)
        }
    },

    modifier = Modifier.fillMaxWidth(),
    ) {
        Text("Transfer AirTime")
    }

    Spacer(modifier = Modifier.height(16.dp))
    // Button for queryBalanceHandler
    Button(
        onClick = {
            TNHandler.queryBalanceHandler(context, inputValue)
        },
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text("Query Balance")
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Button for subscribeDataBundleHandler
    Button(
        onClick = {
            TNHandler.subscribeDataBundleHandler(context, inputValue)
        },
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text("Subscribe Data Bundle")
    }

}

@Composable
fun JivaButtons(){
    val context = LocalContext.current
    Column{
        // JivaLite
        Button(
            onClick = { JivaHandler.jivaLiteHandler(context) },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text("JivaLite")
        }

        // Jiva
        Button(
            onClick = { JivaHandler.jiva(context) },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text("Jiva")
        }

        // JivaPlus
        Button(
            onClick = { JivaHandler.jivaPlus(context) },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text("JivaPlus")
        }
            // JivaSurf
        Button(
            onClick = { JivaHandler.jivaSurf(context) },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text("JivaSurf")
        }

        // JivaSupreme
        Button(
            onClick = { JivaHandler.jivaSupreme(context) },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text("JivaSupreme")
        }

        // JivaSupremeBoost
        Button(
            onClick = { JivaHandler.jivaSupremeBoost(context) },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text("okaAweh")
        }

        // JivaFourteenDay
        Button(
            onClick = {JivaHandler.fourTeenDayJiva(context)},
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ){
            Text("JivaFourteenDay")
        }

        // JivaThirtyDay
        Button(
            onClick = {JivaHandler.thirtyDayJiva(context)},
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ){
            Text("JivaThirtyDay")
        }

        // JivAThirtyOneDay
        Button(
            onClick = {JivaHandler.thirtyOneJiva(context)},
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ){ Text("JivAThirtyOneDay ")}

        //JivaWeekend
        Button(
            onClick = {JivaHandler.weekendJiva(context)},
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ){ Text("JivaWeekend")}
    }
}

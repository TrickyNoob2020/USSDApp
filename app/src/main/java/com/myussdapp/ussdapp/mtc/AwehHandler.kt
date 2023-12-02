package com.myussdapp.ussdapp.MTC

import android.content.Context
import android.content.Intent
import android.net.Uri

object USSDAwehHandler {

    private fun sendUSSDMessage(context: Context, smsText: String) {
        val ussdCode = "*13400#"
        val message = "$ussdCode$smsText"

        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("sms:")
        intent.putExtra("sms_body", message)

        context.startActivity(intent)
    }

    fun okaAweh(context: Context) {
        sendUSSDMessage(context, "#okaAweh#")
    }

    fun awehGo(context: Context) {
        sendUSSDMessage(context, "#AwehGO#")
    }

    fun awehGig(context: Context) {
        sendUSSDMessage(context, "#AwehGig#")
    }

    fun awehPrime(context: Context) {
        sendUSSDMessage(context, "#Aweh#")
    }

    fun superAweh(context: Context) {
        sendUSSDMessage(context, "#SuperAweh#")
    }

    fun awehUltra(context: Context) {
        sendUSSDMessage(context, "#awehultra30#")
    }
}

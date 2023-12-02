package com.myussdapp.ussdapp.tn

import android.content.Context
import android.content.Intent
import android.net.Uri

object JivaHandler {
    fun jivaLiteHandler(context: Context) {
        val ussdCode = "*130*775#"
        startDialerWithUSSD(context, ussdCode)
    }

    fun jiva(context: Context) {
        val ussdCode = "*130*776#"
        startDialerWithUSSD(context, ussdCode)
    }

    fun jivaPlus(context: Context) {
        val ussdCode = "*130*777#"
        startDialerWithUSSD(context, ussdCode)
    }

    fun jivaSurf(context: Context) {
        val ussdCode = "*130*778#"
        startDialerWithUSSD(context, ussdCode)
    }

    fun jivaSupreme(context: Context) {
        val ussdCode = "*130*779#"
        startDialerWithUSSD(context, ussdCode)
    }

    fun jivaSupremeBoost(context: Context) {
        val ussdCode = "*130*788#"
        startDialerWithUSSD(context, ussdCode)
    }

    fun fourTeenDayJiva(context: Context) {
        val ussdCode = "*130*782#"
        startDialerWithUSSD(context, ussdCode)
    }

    fun thirtyDayJiva(context: Context) {
        val ussdCode = "*130*781#"
        startDialerWithUSSD(context, ussdCode)
    }

    fun thirtyOneJiva(context: Context) {
        val ussdCode = "*130*780#"
        startDialerWithUSSD(context, ussdCode)
    }

    fun weekendJiva(context: Context) {
        val ussdCode = "*130*786#"
        startDialerWithUSSD(context, ussdCode)
    }

    private fun startDialerWithUSSD(context: Context, ussdCode: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$ussdCode"))
        context.startActivity(intent)
    }
}

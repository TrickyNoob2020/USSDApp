package com.myussdapp.ussdapp.mtc

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.myussdapp.ussdapp.R

object USSDHandler {
    fun handleAirtimeRecharge(context: Context, value: String) {
        val builder = AlertDialog.Builder(context)
        val rechargeEditText = EditText(context)
        builder.setTitle("Enter Recharge Number:")
        builder.setView(rechargeEditText)
        builder.setPositiveButton("Recharge") { _, _ ->
            val rechargeNumber = rechargeEditText.text.toString()
            if (isValidRechargeNumber(rechargeNumber)) {
                val ussdCode = "*13200*${rechargeNumber}#"
                val uri = Uri.parse("tel:$ussdCode")
                val intent = Intent(Intent.ACTION_CALL, uri)
                // Check for necessary permissions and request permission if required before initiating the call.
                // Ensure you have the CALL_PHONE permission in your AndroidManifest.xml
                // Also consider handling exceptions for devices that do not support USSD calls.
                ContextCompat.startActivity(context, intent, null)
            } else {
                // For example, you can display an error message to the user
            }
        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }
    private fun isValidRechargeNumber(rechargeNumber: String): Boolean {
        // able to check recharge number is valid
        return rechargeNumber.matches(Regex("^\\d{12}$"))
    }

    fun handleCallMeRequest(context: Context, value: String) {
        val builder = AlertDialog.Builder(context)
        val phoneNumberEditText = EditText(context)
        builder.setTitle("Enter Phone Number for Call Me Request")
        builder.setView(phoneNumberEditText)
        builder.setPositiveButton("Request Call") { _, _ ->
            val phoneNumber = phoneNumberEditText.text.toString()
            if (isValidPhoneNumber(phoneNumber)) {
                val ussdCode = "*150*${"$phoneNumber#"}"
                val uri = Uri.parse("tel:$ussdCode")
                val intent = Intent(Intent.ACTION_DIAL, uri)
                // Check for necessary permissions and request permission if required before initiating the call.
                // Ensure you have the CALL_PHONE permission in your AndroidManifest.xml
                // Also consider handling exceptions for devices that do not support USSD calls.
                ContextCompat.startActivity(context, intent, null)
            } else {
                // Invalid phone number, handle accordingly,
                // For example, you can display an error message to the user
            }
        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }
    fun airtimeTransferSMSHandler(context: Context, inputText: String) {
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.airtime_transfer_sms_dialog, null)

        val editTextAmount: EditText = view.findViewById(R.id.editTextAmount)
        val editTextRecipientNumber: EditText = view.findViewById(R.id.editTextRecipientNumber)

        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setView(view)
        alertDialogBuilder
            .setCancelable(false)
            .setPositiveButton("Transfer") { _, _ ->
                val amount = editTextAmount.text.toString().trim()
                val recipientNumber = editTextRecipientNumber.text.toString().trim()

                if (amount.isNotEmpty() && isValidPhoneNumber(recipientNumber)) {
                    val phoneNumber = "13700"

                    // Start the messaging app with the pre-filled message
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("sms:$phoneNumber"))
                    intent.putExtra("sms_body", inputText)
                    context.startActivity(intent)
                } else {
                    // Handle the case where the user did not enter valid information
                    Toast.makeText(context, "Please enter a valid amount and recipient number", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }

        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }




    private fun isValidPhoneNumber(phoneNumber: String): Boolean {
        // able to check phone number is valid
        return phoneNumber.matches(Regex("^081\\d{7}$"))
    }
}

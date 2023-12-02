package com.myussdapp.ussdapp.tn

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.myussdapp.ussdapp.R


object TNHandler{

    fun rechargeHandler(context: Context, value: String) {
        val ussdCode = "124" // Fixed USSD code for recharge

        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder
            .setMessage("Enter the voucher pin:")
            .setCancelable(false)
            .setPositiveButton("Recharge") { _, _ ->
                val editTextVoucherPin: EditText = EditText(context)
                val voucherPin = editTextVoucherPin.text.toString().trim()

                // Combine the USSD code and voucher pin into the complete message
                val message = "$ussdCode*$voucherPin#"

                // Start the messaging app with the pre-filled message
                openMessagingApp(context, message)
            }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }

        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    fun airtimeTransferHandler(context: Context, value: String) {
        val ussdCode = "12400" // Fixed USSD code for airtime transfer

        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.airtime_transfer_dialog, null)

        val editTextRecipient: EditText = view.findViewById(R.id.editTextRecipient)
        val editTextAmount: EditText = view.findViewById(R.id.editTextAmount)

        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setView(view)
        alertDialogBuilder
            .setCancelable(false)
            .setPositiveButton("Transfer") { _, _ ->
                val recipientNumber = editTextRecipient.text.toString().trim()
                val transferAmount = editTextAmount.text.toString().trim()

                // Formulate the transfer message, e.g., "S 0855550000 30"
                val message = "S $recipientNumber $transferAmount"

                // Start the messaging app with the pre-filled message
                openMessagingApp(context, "$ussdCode$message")
            }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }

        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    fun queryBalanceHandler(context: Context, value: String) {
        val ussdCode = "124" // Fixed USSD code for query balance
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$ussdCode"))
        context.startActivity(intent)
    }

    fun subscribeDataBundleHandler(context: Context, value: String) {
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.data_bundle_dialog, null)

        val editTextDataAmount: EditText = view.findViewById(R.id.editTextDataAmount)

        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setView(view)
        alertDialogBuilder
            .setCancelable(false)
            .setPositiveButton("Subscribe") { _, _ ->
                val dataAmountMB = editTextDataAmount.text.toString().trim()

                if (dataAmountMB.isNotEmpty()) {
                    val ussdCode = "*13000*$dataAmountMB#"

                    // Start the dialer app with the USSD code
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$ussdCode"))
                    context.startActivity(intent)
                } else {
                    // Handle the case where the user did not enter a valid data amount
                    Toast.makeText(context, "Please enter a valid data amount", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }

        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }




    @SuppressLint("QueryPermissionsNeeded")
    private fun openMessagingApp(context: Context, ussdMessage: String) {
        val uri = Uri.parse("sms to:$ussdMessage")
        val intent = Intent(Intent.ACTION_SENDTO, uri)

        // Check if there is any messaging app available to handle this intent
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        } else {
            // Handle the case where no messaging app is available,
            // For example, display a toast or show an error message
            Toast.makeText(context, "No messaging app found", Toast.LENGTH_SHORT).show()
        }
    }

}

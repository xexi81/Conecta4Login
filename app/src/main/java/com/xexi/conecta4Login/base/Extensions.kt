package com.xexi.conecta4Login.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.makeText
import com.google.android.material.snackbar.Snackbar
import com.xexi.conecta4Login.R

fun TextView.checkEmpty(): Boolean {
    return TextUtils.isEmpty(this.text)
}

fun Activity.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    makeText(this, message, duration).show()
}

fun View.snackBar(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(
        this,
        message, // Message to show
        duration // How long to display the message.
    ).show()
}

fun Context.startActivity(newClass: Class<*>) {
    startActivity(Intent(this, newClass))
}

fun LinearLayout.drawSquare(color: Int) {
    when(color) {
        0 -> this.setBackgroundResource(R.drawable.casilla_vacia)
        1 -> this.setBackgroundResource(R.drawable.casilla_roja)
        2 -> this.setBackgroundResource(R.drawable.casilla_amarilla)
        else -> this.setBackgroundResource(R.drawable.casilla_vacia)
    }
}


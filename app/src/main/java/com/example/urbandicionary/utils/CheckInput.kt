package com.example.urbandicionary.utils

import android.content.Context
import android.net.ConnectivityManager

class CheckInput(val context: Context) {
    companion object Factory {
        fun isValidInput(name: String?): Boolean {
            return name!!.matches("[a-zA-Z]+".toRegex())
        }
    }
}


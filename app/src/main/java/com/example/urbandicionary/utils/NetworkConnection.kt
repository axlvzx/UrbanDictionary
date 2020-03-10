package com.example.urbandicionary.utils

import android.content.Context
import android.net.ConnectivityManager

class NetworkConnection(val context: Context) {
    companion object Factory {
        fun hasConnection(context: Context): Boolean {
            val connectivityManager=context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo=connectivityManager.activeNetworkInfo
            return  networkInfo!=null && networkInfo.isConnected
        }
    }
}
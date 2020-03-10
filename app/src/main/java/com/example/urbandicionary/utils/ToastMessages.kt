package com.example.urbandicionary.utils

import android.content.Context
import android.widget.Toast

class ToastMessages(val message: String,val context: Context) {
    companion object Factory {
        fun showToast(message: String,context: Context){
            Toast.makeText(context,message,Toast.LENGTH_LONG).show()
        }
    }
}
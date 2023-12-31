package com.example.pushnotifications

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MyFirebaseMessagingService: FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.e("TAG", token)
    }
}
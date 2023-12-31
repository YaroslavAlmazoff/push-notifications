package com.example.pushnotifications

import android.app.Activity
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE = 1001
    private var notificationsStatus: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notificationsStatus = findViewById(R.id.notifications_status)

        notificationsStatus?.setText(if(isNotificationPermissionAllowed(this))
            R.string.notifications_enabled
         else {
            requestNotificationPermission(this)
            R.string.notifications_disabled
        })
    }
    private fun isNotificationPermissionAllowed(context: Context): Boolean {
        val notificationManagerCompat = NotificationManagerCompat.from(context)
        return notificationManagerCompat.areNotificationsEnabled()
    }

    private fun requestNotificationPermission(activity: Activity) {
        val notificationManagerCompat = NotificationManagerCompat.from(activity)
        if (!notificationManagerCompat.areNotificationsEnabled()) {
            val intent = Intent()
            intent.action = "android.settings.APP_NOTIFICATION_SETTINGS"
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                intent.putExtra("android.provider.extra.APP_PACKAGE", activity.packageName)
            } else
                intent.putExtra("app_package", activity.packageName)
                intent.putExtra("app_uid", activity.applicationInfo.uid)

            activity.startActivityForResult(intent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            notificationsStatus?.setText(R.string.notifications_enabled)
        }
    }
}
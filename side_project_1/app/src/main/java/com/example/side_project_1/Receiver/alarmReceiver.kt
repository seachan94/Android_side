package com.example.side_project_1.Receiver

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService

class alarmReceiver : BroadcastReceiver() {

    companion object{
        const val NOTIFICATION_ID = 0
        const val PRIMARY_CHANNEL_ID = "primary_notification_channel"
    }

    lateinit var notificationManager : NotificationManager

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i("tag","sechan Alarm Receiver")

        notificationManager = context?.getSystemService( Context.NOTIFICATION_SERVICE) as NotificationManager

    }


}
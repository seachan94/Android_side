package com.example.side_project_1.Alarm

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.side_project_1.DATA.AlarmData
import com.example.side_project_1.DATA.AppDB
import com.example.side_project_1.R
import kotlinx.android.synthetic.main.register_alarm_view.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object AlarmHandler {


    @RequiresApi(Build.VERSION_CODES.M)
    public fun Add(context : Context, hour : Int, minute : Int, isRetry : Boolean){

        var AlarmDb=AppDB.getInstance(context)

        val addRunnable = Runnable{
            val addAlarm = AlarmData()
            addAlarm.hour = hour
            addAlarm.minitue = minute
            addAlarm.isRtry = isRetry
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                addAlarm.date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            }

            AlarmDb?.dataDao()?.insertAlarm(addAlarm)
        }


        Thread(addRunnable).start()

    }
}
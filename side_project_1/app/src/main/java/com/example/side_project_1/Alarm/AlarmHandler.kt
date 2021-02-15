package com.example.side_project_1.Alarm

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
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
            Log.i("Tag","sechan check log");
            AlarmDb?.dataDao()?.insertAlarm(addAlarm)
        }

        val addThread = Thread(addRunnable)
        addThread.start()
    }


    public interface OnLoadData {
        fun onLoad(alarmDatas: List<AlarmData>)
    }

    public fun getAlarmList(context : Context, onLoadData: OnLoadData){
        var AlarmDb = AppDB.getInstance(context)

        val getRunnable = Runnable {
            var Alarms = AlarmDb?.dataDao()?.getAllAlarm()
            if(Alarms == null)Alarms = listOf<AlarmData>()
            Log.i("tag","sechan "+Alarms)
            onLoadData.onLoad(Alarms)

        }
        val getThread = Thread(getRunnable)
        getThread.start()

    }

    //Alarm 전체 삭제
    public fun deleteAllhandler(context : Context){

        val delAllThread = Thread(
            Runnable{
            var AlarmDb = AppDB.getInstance(context)
            AlarmDb?.dataDao()?.deleteAll()
        }
        )

        delAllThread.start()
    }

    //각각 알람 삭제
    public fun deleteEach(context : Context,idx : Long){

        val delEachThread = Thread(
            Runnable{
                var AlarmDb = AppDB.getInstance(context)
                AlarmDb?.dataDao()?.deleteachAlarm(idx)
            }
        )
        delEachThread.start()
        Log.i("tag","sechan delechh "+idx)
    }

}
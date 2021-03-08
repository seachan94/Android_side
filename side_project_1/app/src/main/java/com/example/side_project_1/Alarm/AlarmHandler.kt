package com.example.side_project_1.Alarm

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.side_project_1.DATA.AlarmData
import com.example.side_project_1.DATA.AppDB
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

        val addThread = Thread(addRunnable)
        addThread.start()
    }


    public interface OnLoadData {
        fun onLoad(alarmDatas: List<AlarmData>): Int
    }


    public fun getAlarmList(context : Context, onLoadData: OnLoadData){ 
        var AlarmDb = AppDB.getInstance(context)

        val getRunnable = Runnable {
            var Alarms = AlarmDb?.dataDao()?.getAllAlarm()
            if(Alarms == null)Alarms = listOf<AlarmData>()
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
        Log.i("tag","sechan check thread "+delAllThread);
        delAllThread.start()
    }

    //각각 알람 삭제
    public fun deleteEach(context: Context, idx: Long?, onLoadData: OnLoadData){

        val delEachThread = Thread(
            Runnable{
                var AlarmDb = AppDB.getInstance(context)
                AlarmDb?.dataDao()?.deleteachAlarm(idx)


                var Alarms = AlarmDb?.dataDao()?.getAllAlarm()
                if(Alarms == null)Alarms = listOf<AlarmData>()
                onLoadData.onLoad(Alarms)
            }
        )
        delEachThread.start()


    }


}
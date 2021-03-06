package com.example.side_project_1.Alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.side_project_1.DATA.AlarmData
import com.example.side_project_1.DATA.AppDB
import com.example.side_project_1.Receiver.alarmReceiver
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object AlarmHandler {

    public interface AlarmM{
        fun getid(hour : Int, min :Int,retry : Boolean,id : Long,context :Context)
    }
    public interface OnLoadData {
        fun onLoad(alarmDatas: List<AlarmData>): Int
        fun onLoadIdx(idxarr : List<Long?>):Long
    }

    @RequiresApi(Build.VERSION_CODES.M)
    public fun Add(context : Context, hour : Int, minute : Int, isRetry : Boolean,setalarm : AlarmM) {

        var alarmid : Long = 0;//db id값 저장

        var AlarmDb=AppDB.getInstance(context)
        val addAlarm = AlarmData()
        val addRunnable = Runnable{

            addAlarm.hour = hour
            addAlarm.minitue = minute
            addAlarm.isRtry = isRetry

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                addAlarm.date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            }
           val check =  AlarmDb?.dataDao()?.insertAlarm(addAlarm)

            if(check != null)alarmid = check

            setalarm.getid(hour,minute,isRetry,alarmid,context)
            Log.i("tag","sechan check alarmid1 "+alarmid)
           }

        val addThread = Thread(addRunnable)
        addThread.start()

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

    public fun getid(context: Context, onLoadData: OnLoadData){
        var AlarmDb = AppDB.getInstance(context)



        val getRunnable = Runnable {
            var Alarms = AlarmDb?.dataDao()?.getAllid()
            if(Alarms == null)Alarms = arrayListOf<Long>()
            onLoadData.onLoadIdx(Alarms)

        }
        val getThread = Thread(getRunnable)
        getThread.start()




    }

    public fun CancelAlarm(context: Context, arr: List<Long?>){

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val alarmIntent = Intent(context,alarmReceiver::class.java).apply{
            action = "com.check.up.setAlarm"
        }

        arr.map{
            if(it != null){
                val pIntent = PendingIntent.getBroadcast(context,it.toInt(),alarmIntent,PendingIntent.FLAG_CANCEL_CURRENT)
                alarmManager.cancel(pIntent)
                pIntent.cancel()
            }

        }

    }

}
package com.example.side_project_1.Receiver

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.getSystemService
import com.example.side_project_1.Alarm.AlarmHandler
import com.example.side_project_1.Alarm.AlarmHandler.OnLoadData
import com.example.side_project_1.DATA.AlarmData
import com.example.side_project_1.DATA.TodoData
import com.example.side_project_1.To_do.TodoHandler

class alarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        val istodo = intent?.getBooleanExtra("isTodo",false)
        val isretry = intent?.getBooleanExtra("isRetry",false)
        val id = intent?.getLongExtra("alarmid",-1)
        if (istodo != null && isretry !=null && id !=null && context!=null) {
            deletealarm(istodo,isretry,id,context)
        }



        val Viewintent = Intent(context,View::class.java)
        Viewintent.putExtra("isTodo",istodo)
        Viewintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        context?.startActivity(Viewintent)
    }

    public fun deletealarm(istodo: Boolean,isretry:Boolean,id : Long,context:Context){
        //알람 울렸을 떄 retry 아닐때를 대비해 삭제한다.

        if(isretry)return

        if(!istodo){
            //해당 알람 삭제
            AlarmHandler.deleteEach(context,id,
                object : AlarmHandler.OnLoadData{
                    override fun onLoad(alarmDatas: List<AlarmData>): Int {
                       return 0
                    }

                    override fun onLoadIdx(idxarr: List<Long?>): Long {
                       return 0
                    }

                })
        }
        else{
            TodoHandler.deleteEach(context,id-10000,object: TodoHandler.OnLoadTodoData{
                override fun onLoad(TodoDatas: List<TodoData>): Int {
                    TODO("Not yet implemented")
                }
            })
        }
    }

}
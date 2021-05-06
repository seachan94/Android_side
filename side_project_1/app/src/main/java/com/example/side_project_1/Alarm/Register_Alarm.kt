package com.example.side_project_1.Alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import com.example.side_project_1.R
import com.example.side_project_1.Receiver.alarmReceiver
import kotlinx.android.synthetic.main.register_alarm_view.*
import java.text.SimpleDateFormat
import java.util.*

class Register_Alarm : AppCompatActivity() {

    var calendar = Calendar.getInstance()


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_alarm_view)
        val days = arrayOf(monday, tuesday, wednesday, thursday, friday, saturday, sunday)
        val select = arrayOf(allday, notweek, week)



        retry.setOnCheckedChangeListener { _, isChecked ->

            layout.isInvisible = !isChecked
        }

        allday.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                for (element in days) {
                    element.isChecked = true;
                }
            }
        }

        notweek.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                days.mapIndexed { index, appCompatCheckBox ->
                    appCompatCheckBox.isChecked = !(index == 5 || index == 6)
                }

            }

        }

        week.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                days.mapIndexed { index, appCompatCheckBox ->
                    appCompatCheckBox.isChecked = index >= 5
                }

            }
        }


        reset.setOnClickListener {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//?
                //os버전과 현재 앱을 지원하는 버전이 위의 조건문을 만족하도록 해야 아래 변수들을 쓸 수 있기 떄문에 다음과 같이 분기 처리를 한다.
                //만일 sdk가 더 옛날 버전이면 처리해 주는 코드가 들어가야 한다.
                time_setting.hour = 0
                time_setting.minute = 0
            }


            //시간 0 00 am


            retry.isChecked = false


            //반복주기 off
            for(element in days){
                element.isChecked = false;
            }
            for(element in select){
                element.isChecked = false;
            }
            //반복 주기 off 시 모든 데이터 초기화화
        }

        enroll.setOnClickListener {

            AlarmHandler.Add(this, time_setting.hour, time_setting.minute, retry.isChecked,
                object : AlarmHandler.AlarmM {
                    override fun getid(
                        hour: Int,
                        min: Int,
                        retry: Boolean,
                        id: Long,
                        context: Context
                    ) {
                        val Msg =
                            time_setting.hour.toString() + " 시 " + time_setting.minute.toString() + " 분 알람 등록"
                        if (id == -1L) {
                            runOnUiThread(Runnable {
                                Toast.makeText(
                                    context,
                                    Msg + "실패",
                                    Toast.LENGTH_SHORT
                                ).show()
                            })
                            return
                        }

                        startAlarm(hour, min, retry, id)
                        //저장되었다는 토스트를 날려준다.
                        runOnUiThread(Runnable {

                            Toast.makeText(
                                context,
                                Msg,
                                Toast.LENGTH_SHORT
                            ).show()

                        })

                    }

                })

        }
    }
    @RequiresApi(Build.VERSION_CODES.M)
    private fun startAlarm(hour: Int, min: Int, retry: Boolean, id: Long){
        //init calendar

        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, min)
        calendar.set(Calendar.SECOND, 0)

        val sdf = SimpleDateFormat("yyyy / MM / dd / HH:mm:ss")
        val datestr: String = sdf.format(calendar.getTime())
        val alarmIntent = Intent(this, alarmReceiver::class.java).apply{
            action = "com.check.up.setAlarm"
        }
        alarmIntent.putExtra("time",datestr)
        alarmIntent.putExtra("isTodo",false)
        alarmIntent.putExtra("isRetry",retry)
        alarmIntent.putExtra("alarmid",id)
        //system service 중 alarm service 가져옴
        alarmIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val alarmManger = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            id.toInt(),
            alarmIntent,
            PendingIntent.FLAG_CANCEL_CURRENT
        )


        if(!retry) {
            alarmManger.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                pendingIntent
            )
        }
        else{
            alarmManger.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,

                AlarmManager.INTERVAL_DAY,
                pendingIntent

            )
        }
        //반복알람 하고 싶다면 interval 삽입하면 됨
        //예를 들면 주기성

    }
}
package com.example.side_project_1.Alarm

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.core.view.isInvisible
import com.example.side_project_1.DATA.AppDB
import com.example.side_project_1.R
import kotlinx.android.synthetic.main.register_alarm_view.*

import kotlin.collections.mapIndexed as mapIndexed

class Register_Alarm : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_alarm_view)
        val days = arrayOf(monday, tuesday, wednesday, thursday, friday, saturday, sunday)
        val select = arrayOf(allday,notweek,week)
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
            Log.i("tag","sechan check "+time_setting.hour+" "+time_setting.minute+" "+ retry.isChecked)
            AlarmHandler.Add(this,time_setting.hour,time_setting.minute,retry.isChecked)

           // AddAlarm1.Add
            //저장되었다는 토스트를 날려준다.

        }
    }

}
package com.example.side_project_1.Alarm

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.side_project_1.Alarm.AlarmHandler.getAlarmList
import com.example.side_project_1.DATA.AlarmData
import com.example.side_project_1.DATA.exData
import com.example.side_project_1.R
import com.example.side_project_1.ViewApater.AlarmViewAdapter
import kotlinx.android.synthetic.main.alarm_recycler_view.*

class ViewAlarm : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.alarm_recycler_view)
        Alarmrecyclerview(this)

    }


    private fun Alarmrecyclerview(context: Context){
        val alarmsList = getAlarmList(this, object: AlarmHandler.OnLoadData {
            override fun onLoad(alarmDatas: List<AlarmData>) {
                val humanAdapter = AlarmViewAdapter(context,alarmDatas)
                alarm_list.adapter = humanAdapter
                Log.i("tag","Sechan check log")
            }

        })

    }
}
package com.example.side_project_1.Alarm

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.side_project_1.Alarm.AlarmHandler.deleteAllhandler
import com.example.side_project_1.Alarm.AlarmHandler.deleteEach
import com.example.side_project_1.Alarm.AlarmHandler.getAlarmList
import com.example.side_project_1.DATA.AlarmData
import com.example.side_project_1.R
import com.example.side_project_1.ViewApater.AlarmViewAdapter
import kotlinx.android.synthetic.main.alarm_recycler_view.*

class ViewAlarm : AppCompatActivity() {
    var Adapter : AlarmViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.alarm_recycler_view)
        Alarmrecyclerview(this)

        Adapter = AlarmViewAdapter(this, arrayListOf())
        alarm_list.adapter = Adapter

        deletall.setOnClickListener {
            delAll(this)
            //Alarmrecyclerview(this)
           // AlarmViewAdapter(this,null).notifyDataSetChanged()
            Log.i("tag","sechan check changeView")

        }


    }

    public fun initRecyclerView(context: Context){

    }
    private fun Alarmrecyclerview(context: Context) {
        val alarmsList = getAlarmList(this, object : AlarmHandler.OnLoadData {
            override fun onLoad(alarmDatas: List<AlarmData>) {
                Adapter?.data = alarmDatas
                Adapter?.notifyDataSetChanged()
            }
        })
    }
    public fun delAll(context: Context) {
        deleteAllhandler(this)
        Adapter?.notifyDataSetChanged()
    }

    public fun delEach() {
        deleteEach(this, 1)
    }
}
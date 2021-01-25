package com.example.side_project_1.Alarm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.side_project_1.DATA.exData
import com.example.side_project_1.R
import com.example.side_project_1.ViewApater.AlarmViewAdapter
import kotlinx.android.synthetic.main.alarm_recycler_view.*

class ViewAlarm : AppCompatActivity() {
    var exdataset = arrayListOf<exData>(
        exData("S",1,1,false),
        exData("S",1,1,false),
        exData("S",1,1,false),
        exData("S",1,1,false),
        exData("S",1,1,false),
        exData("S",1,1,false),
        exData("S",1,1,false),
        exData("S",1,1,false),
        exData("S",1,1,false),
        exData("S",1,1,false),
        exData("S",1,1,false),
        exData("S",1,1,false),
        exData("S",1,1,false),
        exData("S",1,1,false),
        exData("S",1,1,false),
        exData("S",1,1,false),
        exData("S",1,1,false),
        exData("S",1,1,false),
        exData("S",1,1,false),
        exData("S",1,1,false),
        exData("S",1,1,false)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.alarm_recycler_view)
        exAlarmrecyclerview()
    }


    private fun exAlarmrecyclerview(){
        val exadapter = AlarmViewAdapter(this,exdataset)
        alarm_list.adapter = exadapter

    }
}
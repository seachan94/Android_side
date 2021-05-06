package com.example.side_project_1.Receiver

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.side_project_1.R
import kotlinx.android.synthetic.main.alarm_noti.*

class View : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.alarm_noti)

        val intent = getIntent()

        val isTodo = intent.getBooleanExtra("isTodo",false)
        setText(isTodo)


    }

    public fun setText(isTodo : Boolean){
        if(!isTodo)viewtext.text = "알람 맞췄는데 안볼꺼면 \n 왜 맞춤..?...?"
        else viewtext.text = "할일 다 햇냐....?"
    }
}
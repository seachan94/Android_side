package com.example.side_project_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.side_project_1.Alarm.Register_Alarm
import com.example.side_project_1.Alarm.ViewAlarm
import com.example.side_project_1.To_do.RegisterTodo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        register_alarm.setOnClickListener {
            clickButton<Register_Alarm>()
        }
        view_alarm.setOnClickListener{
            clickButton<ViewAlarm>()
        }

        register_Todo.setOnClickListener{
            clickButton<RegisterTodo>()
        }
    }

    inline fun <reified T> clickButton() {

        val intent =Intent(this,T::class.java )
        startActivity(intent)
    }
}
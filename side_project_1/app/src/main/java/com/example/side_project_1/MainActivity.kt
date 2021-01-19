package com.example.side_project_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.side_project_1.Alarm.Register_Alarm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        register_alarm.setOnClickListener {
            clickButton<Register_Alarm>()
        }

    }

    inline fun <reified T> clickButton() {

        val intent =Intent(this,T::class.java )
        startActivity(intent)
    }
}
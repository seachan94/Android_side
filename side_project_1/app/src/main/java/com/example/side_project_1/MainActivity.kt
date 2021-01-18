package com.example.side_project_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        register_alarm.setOnClickListener {
            clickButton<Register_Alarm>()
        }

    }

    inline fun <reified T> clickButton() {
//        when(view.id) {id
//            register_alarm.id -> {Log.i("dd", "")}
//        }
//        view.id == R.id.register_alarm
        val intent =Intent(this,T::class.java )
        startActivity(intent)
    }
}
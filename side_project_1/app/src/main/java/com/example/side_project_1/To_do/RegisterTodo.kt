package com.example.side_project_1.To_do

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.side_project_1.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.register_alarm_view.*
import kotlinx.android.synthetic.main.register_todo_view.*

class RegisterTodo: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_todo_view)

        btnregister.setOnClickListener() {
            Log.i("tag","sechan check btn "+TodoText.text)
        }
    }
}
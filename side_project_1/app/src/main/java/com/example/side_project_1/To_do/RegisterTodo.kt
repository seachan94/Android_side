package com.example.side_project_1.To_do

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.side_project_1.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.register_alarm_view.*
import kotlinx.android.synthetic.main.register_todo_view.*

class RegisterTodo: AppCompatActivity() {

    private var deadlinetime =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_todo_view)

        btnregister.setOnClickListener() {
            enrollBtnfun()
        }
        deadline.setOnClickListener(){
            selectDeadline()
        }
    }


    private fun enrollBtnfun(){
        val todo = TodoText.text.toString();

        if("".equals(todo)){
            val Msg ="해야 할 일을 확인해 주세요 : ("
            Toast.makeText(
                this,
                Msg,
                Toast.LENGTH_SHORT
            ).show()
        }

        else{
            val Msg =todo + "가 저장되었어요 : )"
            Toast.makeText(
                this,
                Msg,
                Toast.LENGTH_SHORT
            ).show()

            TodoHandler.AddTodo(this,todo,deadlinetime)
        }

    }
    private fun selectDeadline(){

        val dialog = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            DatePickerDialog(this, object: DatePickerDialog.OnDateSetListener{

                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

                    deadlinetime = year.toString()+month.toString()+dayOfMonth.toString()
                    timePicker()

                }

            }, 2021, 2, 2)
        } else {
            TODO("VERSION.SDK_INT < N")
        }
        dialog.show()

    }
    private fun  timePicker( ) {
        val timeDialog = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            TimePickerDialog(
                this,
                R.style.sechanTheme,
                object:TimePickerDialog.OnTimeSetListener{
                    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                        deadlinetime += hourOfDay.toString() + minute.toString()
                        Log.i("tag","sechan check final "+deadlinetime)
                    }

                },
           0,0,true )


        } else {
            TODO("VERSION.SDK_INT < N")
        }

        timeDialog.show()

    }
}
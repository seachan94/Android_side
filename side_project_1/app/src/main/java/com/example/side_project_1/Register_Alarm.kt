package com.example.side_project_1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.register_alarm_view.*
import kotlin.collections.mapIndexed as mapIndexed

class Register_Alarm : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_alarm_view)
        val days = arrayOf(monday, tuesday, wednesday, thursday, friday, saturday, sunday)
        retry.setOnCheckedChangeListener { _, isChecked ->
            layout.isInvisible = !isChecked
        }
        Log.i("TAG", "ddkf ${days[1]}")

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

    }
}
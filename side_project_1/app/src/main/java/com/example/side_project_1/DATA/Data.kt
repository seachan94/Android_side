package com.example.side_project_1.DATA

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//데이터 형식을 정해준다.
@Entity(tableName = "DataAlarm")
class AlarmData (
    @PrimaryKey(autoGenerate = true) var id: Long=0,
    var date: String = "",
    var hour: Int = -1,
    var minitue: Int = -1,
    var isRtry: Boolean = false
)

@Entity(tableName = "DataTodo")
class TodoData(
    @PrimaryKey(autoGenerate = true)var id: Long = 0,
    var isAlarm : Boolean = false,
    var date: String = "",
    var time: String = "",
    var isRtry: Boolean = false,
    var todoContent : String = ""
)

class exData(var date : String, var hour : Int , var minitue : Int , var isRtry : Boolean)
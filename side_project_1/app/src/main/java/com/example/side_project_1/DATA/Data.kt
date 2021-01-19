package com.example.side_project_1.DATA

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DataAlarm")
class AlarmData (
    @PrimaryKey(autoGenerate = true) var id: Long=-1,
    var date: String = "",
    var time: String = "",
    var isRtry: Boolean = false
)

@Entity(tableName = "DataTodo")
class TodoData(
    @PrimaryKey(autoGenerate = true)var id: Long = -1,
    var isAlarm : Boolean = false,
    var date: String = "",
    var time: String = "",
    var isRtry: Boolean = false,
    var todoContent : String = ""
)
package com.example.side_project_1.DATA

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

//@Entity(tableName = "DataAlarm")AlarmData
//@Entity(tableName = "DataTodo")TodoData
@Dao
interface DataDao {

    @Query("SELECT * FROM DataAlarm")
    fun getAllAlarm():List<AlarmData>

    @Insert(onConflict = 1)
    fun insertAlarm(alarmData:AlarmData)


    @Query("DELETE FROM DataAlarm WHERE id = :id")
    fun deletAlarm(id :Long)


}
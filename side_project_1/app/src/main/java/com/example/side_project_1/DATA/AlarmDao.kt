package com.example.side_project_1.DATA

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

//@Entity(tableName = "DataAlarm")AlarmData
//@Entity(tableName = "DataTodo")TodoData
@Dao
interface AlarmDao {

    @Query("SELECT * FROM DataAlarm")
    fun getAllAlarm():List<AlarmData>


    //onConflict의 의미는 동일 primary key가 있을 때 덮어 쓴다는 의미이다.
    //1 = REPLACE
    @Insert(onConflict = 1)
    fun insertAlarm(alarmData:AlarmData):Long

    @Query("DELETE FROM DataAlarm WHERE id = :id")
    fun deleteachAlarm(id: Long?)

    @Query("DELETE FROM DataAlarm")
    fun deleteAll()

    @Query("SELECT id FROM DataAlarm")
    fun getAllid():List<Long>


}
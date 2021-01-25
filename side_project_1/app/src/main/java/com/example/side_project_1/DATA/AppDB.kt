package com.example.side_project_1.DATA

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


//@Entity(tableName = "DataAlarm")AlarmData
//@Entity(tableName = "DataTodo")TodoData

@Database(entities = [AlarmData::class], version = 1)
abstract class AppDB:RoomDatabase(){

    abstract fun dataDao(): AlarmDao

    companion object{
        private var INSTANCE: AppDB? = null

        fun getInstance(context: Context): AppDB? {
            if(INSTANCE == null){

                synchronized(AppDB::class){
                    INSTANCE =Room.databaseBuilder(
                        context.applicationContext,
                        AppDB::class.java,
                        "sechanDB").fallbackToDestructiveMigration().build()
                }

            }
//
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }


    }
}
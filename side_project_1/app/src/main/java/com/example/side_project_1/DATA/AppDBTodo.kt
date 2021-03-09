package com.example.side_project_1.DATA

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TodoData::class],version = 1)

abstract class AppDBTodo:RoomDatabase(){

    abstract fun dataDao():TodoDao

    companion object{
        private var INSTANCE : AppDBTodo? = null

        fun getInstance(context : Context):AppDBTodo?{
            if(INSTANCE == null){
                synchronized(AppDBTodo::class){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDBTodo::class.java,
                        "sechanDB"
                    ).fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}
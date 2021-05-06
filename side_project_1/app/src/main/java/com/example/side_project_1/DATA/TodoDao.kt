package com.example.side_project_1.DATA

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao {

    @Query("SELECT * FROM DataTodo")
    fun getAllTodo():List<TodoData>


    @Insert(onConflict = 1)
    fun insertTodo(todoData : TodoData):Long

    @Query("DELETE FROM DataTodo WHERE id = :id")
    fun deleteachTodo(id: Long?)

    @Query("DELETE FROM DataTodo")
    fun deleteAll()
}
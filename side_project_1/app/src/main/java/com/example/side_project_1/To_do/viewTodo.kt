package com.example.side_project_1.To_do

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.side_project_1.DATA.TodoData
import com.example.side_project_1.R
import com.example.side_project_1.To_do.TodoHandler.deletAllTodo
import com.example.side_project_1.To_do.TodoHandler.getTodoList
import com.example.side_project_1.ViewApater.AlarmViewAdapter

import com.example.side_project_1.ViewApater.TodoViewAdapter
import kotlinx.android.synthetic.main.alarm_recycler_view.*
import kotlinx.android.synthetic.main.todo_recycler_view.*

class viewTodo: AppCompatActivity() {
    //adapter 추가
    var Adapter : TodoViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.todo_recycler_view)



        Adapter = TodoViewAdapter(this, arrayListOf())
        loadTodoData(this)

        todo_list.adapter = Adapter
        tododelall.setOnClickListener {
            dellAll(this)
        }


    }

    private fun loadTodoData(context : Context){
        val TodoList = getTodoList(context,
        object : TodoHandler.OnLoadTodoData{
            override fun onLoad(TodoDatas: List<TodoData>): Int {
                Adapter?.datas = TodoDatas
                Adapter?.notifyDataSetChanged()
                return 0;
            }

        }
            )
    }
    private fun dellAll (context : Context){
        deletAllTodo(context)
        Adapter?.datas = arrayListOf()
        Adapter?.notifyDataSetChanged()
    }
}
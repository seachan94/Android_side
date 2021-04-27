package com.example.side_project_1.To_do

import android.content.Context
import android.util.Log
import com.example.side_project_1.DATA.AppDBTodo
import com.example.side_project_1.DATA.TodoData

object TodoHandler {


    public interface OnLoadTodoData {
        fun onLoad(TodoDatas: List<TodoData>): Int
    }



    public fun AddTodo(context : Context, todomsg : String, date: String){
        //DB intsance를 불러와야 한다

        var TodoDB = AppDBTodo.getInstance(context)

        val addRunnable = Runnable {
            val todoData = TodoData()
            todoData.todoContent = todomsg
            todoData.deadline = date
            TodoDB?.dataDao()?.insertTodo(todoData)
        }
        val addThread = Thread(addRunnable)
        addThread.start()
    }


    public fun getTodoList(context : Context, onLoadTodo: OnLoadTodoData){
        var TodoDB = AppDBTodo.getInstance(context)

        val getRunnable = Runnable {
            var Todo = TodoDB?.dataDao()?.getAllTodo()
            if(Todo == null)Todo = listOf<TodoData>()
            onLoadTodo.onLoad(Todo)

        }
        val getThread = Thread(getRunnable)
        getThread.start()

    }
    public fun deletAllTodo(context:Context){
        var TodoDB = AppDBTodo.getInstance(context)
        val delRunnable = Thread (
            Runnable {
                TodoDB?.dataDao()?.deleteAll()

            }
        )
        delRunnable.start()

    }
    public fun deleteEach(context: Context, id: Long?, onLoadTodo: OnLoadTodoData){
       val delEachThread = Thread(
           Runnable{
               var ToDoDB = AppDBTodo.getInstance(context)
               ToDoDB?.dataDao()?.deleteachTodo(id)

               var Todo = ToDoDB?.dataDao()?.getAllTodo()
               if(Todo == null)Todo = listOf<TodoData>()
               onLoadTodo.onLoad(Todo)

           }
       )
        delEachThread.start()
    }


}
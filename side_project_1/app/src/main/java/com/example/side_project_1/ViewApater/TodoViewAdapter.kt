package com.example.side_project_1.ViewApater

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.side_project_1.DATA.TodoData
import com.example.side_project_1.R

import kotlinx.android.synthetic.main.viewtodo.view.*


class TodoViewAdapter(
    val context: Context,
    var datas: List<TodoData>?,
    val onclickDeleteEvent : onClickEvent):
    RecyclerView.Adapter<TodoViewAdapter.Holder>() {

    public interface onClickEvent{
        fun onClickDelete(id : Long?)
    }
    inner class Holder(view: View?):RecyclerView.ViewHolder(view!!){
        val content = view?.todocontent
        val deadline = view?.tododeadline
        val delEachbtn = view?.todoeachdel

        fun bind(data : TodoData?){
            content?.text = data?.todoContent
            if(data?.deadline != ""){

                deadline?.text = data?.deadline
            }
            delEachbtn?.setOnClickListener {
                onclickDeleteEvent.onClickDelete(data?.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewAdapter.Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.viewtodo,parent,false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: TodoViewAdapter.Holder, position: Int) {
        return holder.bind(datas?.get(position))
    }

    override fun getItemCount(): Int {
        return datas?.size!!
    }
}
package com.example.side_project_1.ViewApater

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.side_project_1.DATA.AlarmData
import com.example.side_project_1.DATA.exData
import com.example.side_project_1.R
import kotlinx.android.synthetic.main.viewalarm.view.*

class AlarmViewAdapter(val context: Context, val data : List<AlarmData>):
        RecyclerView.Adapter<AlarmViewAdapter.Holder>(){


    // ? -> !!
    inner class Holder(view : View?):RecyclerView.ViewHolder(view!!){
        val date = view?.date
        val hour = view?.hour
        val minu = view?.minute
        val isRetry = view?.isRetryinview

        fun bind(datas : AlarmData?){
            Log.i("TAG", "ex data : ${date}")
            date?.text = datas?.date
            hour?.text = datas?.hour.toString()
            minu?.text = datas?.minitue.toString()
            isRetry?.text = datas?.isRtry.toString()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewAdapter.Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.viewalarm,parent,false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: AlarmViewAdapter.Holder, position: Int) {
        Log.i("TAG", "Bind ${data[position]}")
        return holder.bind(data[position])
    }



}
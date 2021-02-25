package com.example.side_project_1.ViewApater

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.side_project_1.Alarm.AlarmHandler
import com.example.side_project_1.DATA.AlarmData
import com.example.side_project_1.R
import kotlinx.android.synthetic.main.viewalarm.view.*

class AlarmViewAdapter(
    val context: Context,
    var data: List<AlarmData>?
):
        RecyclerView.Adapter<AlarmViewAdapter.Holder>(){


    // ? -> !!
    inner class Holder(view : View?):RecyclerView.ViewHolder(view!!){
        val date = view?.date
        val hour = view?.hour
        val minu = view?.minute
        val isRetry = view?.isRetryinview
        val delBtn = view?.eachdelete

        fun bind(datas : AlarmData?){

            date?.text = datas?.date
            hour?.text = datas?.hour.toString()
            minu?.text = datas?.minitue.toString()
            isRetry?.text = datas?.isRtry.toString()

            /*delBtn?.setOnClickListener {
                object : AlarmHandler.OnLoadData {
                    override fun onLoad(alarmDatas: List<AlarmData>): Int {
                        TODO("Not yet implemented")
                    }

                    override fun getPosition(): Long {
                        TODO("Not yet implemented")
                    }

                    fun returnPosition(alarmDatas: List<AlarmData>): Int {
                        return position
                    }
                }
            }*/


        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewAdapter.Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.viewalarm,parent,false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        if (data != null) {
            return data!!.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: AlarmViewAdapter.Holder,position:Int) {
        Log.i("tag","sechan check onBind")
        return holder.bind(data?.get(position))
    }

}
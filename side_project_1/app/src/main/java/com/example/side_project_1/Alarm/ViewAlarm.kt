    package com.example.side_project_1.Alarm

    import android.content.Context
    import android.os.Bundle
    import android.util.Log
    import androidx.appcompat.app.AppCompatActivity
    import com.example.side_project_1.Alarm.AlarmHandler.deleteAllhandler
    import com.example.side_project_1.Alarm.AlarmHandler.deleteEach
    import com.example.side_project_1.Alarm.AlarmHandler.getAlarmList
    import com.example.side_project_1.DATA.AlarmData
    import com.example.side_project_1.R
    import com.example.side_project_1.ViewApater.AlarmViewAdapter
    import kotlinx.android.synthetic.main.alarm_recycler_view.*
    import kotlinx.android.synthetic.main.viewalarm.*

    class ViewAlarm : AppCompatActivity(), AlarmViewAdapter.OnClickEvent {

        var Adapter : AlarmViewAdapter? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.alarm_recycler_view)
            Alarmrecyclerview(this)

            Adapter = AlarmViewAdapter(this, arrayListOf(), this)

            alarm_list.adapter = Adapter

            deletall.setOnClickListener {
                delAll(this)
            }
        }


        //data 없으면 꺼짐
        private fun Alarmrecyclerview(context: Context) {

            val alarmsList = getAlarmList(this,
                object : AlarmHandler.OnLoadData {

                    override fun onLoad(alarmDatas: List<AlarmData>): Int {
                        Adapter?.data = alarmDatas
                        Adapter?.notifyDataSetChanged()
                        return 0
                    }
                })

        }
        private fun delAll(context: Context) {
            deleteAllhandler(this)
            Adapter?.data = arrayListOf();
            Adapter?.notifyDataSetChanged()
        }

        override fun onClickDelete(id: Long?) {
            val context = this;
           deleteEach(this,id,object : AlarmHandler.OnLoadData {

               override fun onLoad(alarmDatas: List<AlarmData>): Int {
                   runOnUiThread(Runnable {

                       Adapter?.data = alarmDatas
                       Adapter?.notifyDataSetChanged()

                   })
                   return 0
               }
           });

        }


    }
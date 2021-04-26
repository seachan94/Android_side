    package com.example.side_project_1.Alarm

    import android.content.Context
    import android.os.Bundle
    import androidx.appcompat.app.AppCompatActivity
    import com.example.side_project_1.Alarm.AlarmHandler.deleteAllhandler
    import com.example.side_project_1.Alarm.AlarmHandler.deleteEach
    import com.example.side_project_1.Alarm.AlarmHandler.getAlarmList
    import com.example.side_project_1.Alarm.AlarmHandler.getid
    import com.example.side_project_1.DATA.AlarmData
    import com.example.side_project_1.R
    import com.example.side_project_1.ViewApater.AlarmViewAdapter
    import kotlinx.android.synthetic.main.alarm_recycler_view.*

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

                    override fun onLoadIdx(idxarr: List<Long?>): Long {
                        TODO("Not yet implemented")
                    }
                })

        }
        private fun delAll(context: Context) {

            getid(context,
                object : AlarmHandler.OnLoadData{
                    override fun onLoad(alarmDatas: List<AlarmData>): Int {
                        TODO("Not yet implemented")
                    }

                    override fun onLoadIdx(idxarr: List<Long?>): Long {
                        AlarmHandler.CancelAlarm(context,idxarr)
                        return 0;
                    }

                })

            deleteAllhandler(this)
            Adapter?.data = arrayListOf();
            Adapter?.notifyDataSetChanged()
        }

        override fun onClickDelete(id: Long?) {
            val context = this;
            val idlist = listOf(id)

            AlarmHandler.CancelAlarm(this,idlist)
            deleteEach(this,id,object : AlarmHandler.OnLoadData {

               override fun onLoad(alarmDatas: List<AlarmData>): Int {
                   runOnUiThread(Runnable {

                       Adapter?.data = alarmDatas
                       Adapter?.notifyDataSetChanged()

                   })
                   return 0
               }

               override fun onLoadIdx(idxarr: List<Long?>): Long {
                   TODO("Not yet implemented")
               }
           });

        }


    }
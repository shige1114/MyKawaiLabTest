/*
* Created by 濱口　滋久
* 研究に使用するためのコード
* 収集する情報は任意の時間帯に使用されたアプリの名前、使用時間、最後に使用した時間
* 他の情報も追加するかも
*
* 収集したい情報があるなら、関数を追加すれば良い。
* */



package com.kawailab.mykawailabtest

import android.app.usage.UsageEvents.Event
import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.util.Log

class GetAccountInfo(
    private val usageStatsManager: UsageStatsManager
){

    private val intervalTime = UsageStatsManager.INTERVAL_BEST
    private val startTime = System.currentTimeMillis()-this.fixMinutes(24*60)
    private val end = System.currentTimeMillis()
    fun test():List<UsageStats>{
        val ls = usageStatsManager.queryUsageStats(intervalTime,startTime,end)
        val e = usageStatsManager.queryEvents(startTime,end)
        while (e.hasNextEvent()) {
            val event = Event()
            e.getNextEvent(event)
            val timestamp = event.timeStamp
            val packageName = event.packageName

            val type = event.eventType
            if(type==Event.ACTIVITY_PAUSED || type==Event.ACTIVITY_RESUMED){
                Log.i("data",timestamp.toString())
                Log.i("data",packageName.toString())
                Log.i("data",type.toString())
            }

        }
        return ls
    }

    private fun fixMinutes(x:Int):Long{
        val v = x*60*1000
        return v.toLong()
    }




    private fun toString(usageStats:UsageStats):List<Any>{
        return  mutableListOf(usageStats.packageName,usageStats.lastTimeStamp,usageStats.totalTimeInForeground)
    }


}
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
import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity


class GetAccountInfo(

){
    private lateinit var  usageStatsManager: UsageStatsManager
    private val startTime = System.currentTimeMillis()-this.fixMinutes(10)
    private val end = System.currentTimeMillis()
    private val usefulType = mutableListOf(
        Event.ACTIVITY_PAUSED,
        Event.ACTIVITY_RESUMED,
        Event.DEVICE_SHUTDOWN,
        Event.DEVICE_STARTUP,
        Event.SCREEN_INTERACTIVE,
        Event.SCREEN_NON_INTERACTIVE
    )

    fun test(startT:Long=0,endT:Long=0):List<Any>{
        val eventStats = usageStatsManager.queryEvents(startTime,end)
        val value = mutableListOf<Any>()
        val eventStat = Event()
        while (eventStats.hasNextEvent()) {
            eventStats.getNextEvent(eventStat)
            if(this.checkUsefulData(eventStat)){
                value.add(this.toEventStringList(eventStat))
                Log.i("scs","success")
            }
        }
        return value
    }

    private fun checkUsefulData(event:Event):Boolean{
        return event.eventType in this.usefulType
    }

    private fun fixMinutes(x:Int):Long{
        val v = x*60*1000
        return v.toLong()
    }
    private fun fixHour(x:Int):Long{
        val v = x*60*60*1000
        return v.toLong()
    }

    private fun toUsageStatsStringList(usageStats:UsageStats):List<Any>{
        return  mutableListOf(
            usageStats.packageName.toString(),
            usageStats.lastTimeStamp.toString(),
            usageStats.totalTimeInForeground.toString()
        )
    }

    private fun toEventStringList(event:Event):List<String>{
        return mutableListOf(
            event.timeStamp.toString(),
            event.packageName.toString(),
            event.eventType.toString()
        )
    }


    companion object{
        @Volatile private var instance: GetAccountInfo ? = null

        fun getInstance(usm:UsageStatsManager): GetAccountInfo {
            return (instance ?: synchronized(this){
                GetAccountInfo().also {
                    it.usageStatsManager = usm
                    instance = it
                }
            })
        }

    }






}
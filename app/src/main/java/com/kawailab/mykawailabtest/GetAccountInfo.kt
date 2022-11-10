package com.kawailab.mykawailabtest

import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.util.Log
class GetAccountInfo(
    private val usageStatsManager: UsageStatsManager
){
    private val intervalTime = UsageStatsManager.INTERVAL_BEST
    private val startTime = System.currentTimeMillis() - 24 * 60 * 60 * 1000
    private val end = System.currentTimeMillis()
    fun test():List<UsageStats>{
        val l = usageStatsManager.queryUsageStats(intervalTime,startTime,end)
        Log.i("success","success")
        return l
    }


}
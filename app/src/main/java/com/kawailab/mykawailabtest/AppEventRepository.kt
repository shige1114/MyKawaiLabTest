package com.kawailab.mykawailabtest



class AppEventRepository(private val appEventDao:AppEventDao) {

    fun getAppEvents(start_time:Long,end_time:Long) =
        appEventDao.getAppEvents(start_time, end_time)

    fun getAppEvent(id:Long) =
        appEventDao.getAppEvent(id)

    suspend fun insertAll(appEvents:List<AppEvent>) =
        appEventDao.insertAll(appEvents)

    companion object {
        @Volatile private var instance: AppEventRepository ?= null

        fun getInstance (appEventDao: AppEventDao){
            instance ?: synchronized(this){
                instance ?: AppEventRepository(appEventDao).also { instance = it }
            }
        }
    }

}
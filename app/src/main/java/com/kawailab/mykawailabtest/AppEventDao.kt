package com.kawailab.mykawailabtest
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AppEventDao {

    @Query("SELECT * FROM app_events WHERE id = :eventId")
    fun getAppEvent(eventId: Long): Flow<AppEvent>

    @Query("SELECT * FROM app_events WHERE time_stamp >= :start_time and time_stamp <= :end_time")
    fun getAppEvents(start_time:Long,end_time:Long): Flow<List<AppEvent>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(appEvents: List<AppEvent>)
}
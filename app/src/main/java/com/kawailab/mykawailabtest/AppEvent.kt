package com.kawailab.mykawailabtest

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "app_events")
data class AppEvent(
    @PrimaryKey @ColumnInfo(name = "id") val eventId: Long,
    val name: String,
    val type: Int,
    val time_stamp: Long,
) {
    companion object {
        private val typeName:Map<Int,String> =
            mapOf(2 to "ACTIVITY_PAUSED",
                1 to "ACTIVITY_RESUMED",
                26 to "DEVICE_SHUTDOWN",
                27 to "DEVICE_SHUTDOWN",
                19 to "DEVICE_STARTUP",
                15 to "SCREEN_INTERACTIVE",
                28 to "SCREEN_NON_INTERACTIVE",
            )
    }
    override fun toString(): String {
        return "%s,%s,%s\n".
        format(
            this.name,
            this.type.toString(),
            this.time_stamp.toString()
        )
    }


}

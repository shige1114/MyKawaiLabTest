package com.kawailab.mykawailabtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.Manifest
import android.app.AppOpsManager
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Process
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.kawailab.mykawailabtest.GetAccountInfo

class MainActivity : AppCompatActivity() {
    private lateinit var monitor:GetAccountInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(checkReadStatsPermission("android.permission.PACKAGE_USAGE_STATS")){
            val usageManager=getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
            monitor = GetAccountInfo.getInstance(usageManager)
            monitor.test()
        }
    }

    /*
    * アプリの使用履歴取得の権限チェック
    * 位置情報についての権限チェック
    * 他の権限とは違う
    * */
    private fun checkReadStatsPermission(permissionRequestString:String): Boolean {
        // AppOpsManagerを取得
        val aom = getSystemService(APP_OPS_SERVICE) as AppOpsManager
        // GET_USAGE_STATSのステータスを取得
        val mode = aom.unsafeCheckOp(
            AppOpsManager.OPSTR_GET_USAGE_STATS,
            Process.myUid(),
            packageName
        )
        return if (mode == AppOpsManager.MODE_DEFAULT) {
            // AppOpsの状態がデフォルトなら通常のpermissionチェックを行う。
            // 普通のアプリならfalse
            checkPermission(
                permissionRequestString,
                Process.myPid(),
                Process.myUid()
            ) == PackageManager.PERMISSION_GRANTED
        } else mode == AppOpsManager.MODE_ALLOWED
        // AppOpsの状態がデフォルトでないならallowedのみtrue
    }
}
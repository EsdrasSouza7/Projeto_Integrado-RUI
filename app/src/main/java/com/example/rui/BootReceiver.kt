package com.example.rui

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            // Agendar o primeiro serviço logo após o boot
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val serviceIntent = Intent(context, NotificationService::class.java)
            val pendingIntent = PendingIntent.getService(context, 0, serviceIntent, PendingIntent.FLAG_IMMUTABLE)

            // Agendar para rodar imediatamente
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pendingIntent)
            }
        }
}

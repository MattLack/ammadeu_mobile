package com.hack.ammadeu.adapter.firebase_notification

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.app.NotificationManager
import android.media.RingtoneManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import com.hack.ammadeu.MainScreenActivity
import com.hack.ammadeu.R





//@SuppressLint("Registered")
class MyFirebaseMessagingService : FirebaseMessagingService() {

    lateinit var builder : Notification.Builder
    val TAG = "FirebaseMessagingService"
    private val channelId = "com.hack.ammadeu"
    private val description = "Just a description"

    //@SuppressLint("LongLogTag")
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG, "Dikirim dari: ${remoteMessage.from}")

        if (remoteMessage.notification != null) {
            this.createNotificationChannel()
            showNotification(0,
                remoteMessage.notification?.title, remoteMessage.notification?.body)
        }
    }

    private fun showNotification(id: Int,title: String?, body: String?) {

        var builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(body)
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText(body))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            notify(id, builder.build())
        }


    }
    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Any name"
            //val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(this.channelId, name, importance).apply {
                description = "We are just testing"
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }


}
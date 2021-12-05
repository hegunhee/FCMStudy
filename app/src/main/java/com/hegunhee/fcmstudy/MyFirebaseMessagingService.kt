package com.hegunhee.fcmstudy

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        Log.d("Test","From: " + remoteMessage!!.from)

        if(remoteMessage.data.isNotEmpty()){
            val data = remoteMessage.data
            Log.d("바디: ",remoteMessage.data.toString())
            Log.d("바디?","hello1 ")
            Log.d("타이틀: ",data["title"] ?: "타이틀 찾지못함")
            Log.d("메세지: ",data["message"] ?: "메세지도 찾지못함")
            Log.d("바디?","hello2 ")


            createNotificationChannel()

            val type = remoteMessage.data["type"]?.let { NotificationType.valueOf(it) }
            val title = remoteMessage.data["title"]
            val message = remoteMessage.data["message"]
            type ?: return



            NotificationManagerCompat.from(this)
                .notify(type.id,createNotification(type,title,message))
        }else{
            Log.d("수신에러: ","data가 비어있습니다. 메시지를 수신하지 못했습니다.")
            Log.d("data 값: ", remoteMessage.data.toString())
        }
    }

    private fun createNotification(type : NotificationType,title : String?, message : String?): Notification{
        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        when(type){
            NotificationType.NORMAL -> Unit
            NotificationType.EXPANDABLE -> {
                notificationBuilder.setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText(
                            "BIGSTYLE"
                        )
                )
            }
            NotificationType.CUSTOM -> {

            }
        }
        return notificationBuilder.build()
    }
    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = CHANNEL_NAME
            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
                .createNotificationChannel(channel)
        }
    }
    companion object{
        const val CHANNEL_ID = "id"
        const val CHANNEL_NAME = "name"
        const val CHANNEL_DESC = "채널 설명입니다."
    }

    private fun sendNotification(remoteMessage: RemoteMessage){

    }

}
package com.hegunhee.fcmstudy

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
            sendNotification(remoteMessage)
        }else{
            Log.d("수신에러: ","data가 비어있습니다. 메시지를 수신하지 못했습니다.")
            Log.d("data 값: ", remoteMessage.data.toString())
        }
    }

    private fun sendNotification(remoteMessage: RemoteMessage){

    }

}
package com.hegunhee.fcmstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.hegunhee.fcmstudy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener {task ->
            if (!task.isSuccessful) {
                Log.d("Test", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            binding.tokenText.text = token ?: "토큰이 없읍니다"
            Log.d("test", binding.tokenText.text.toString())
        })
    }
}
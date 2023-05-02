package com.example.votingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.VideoView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var videoView : VideoView
        videoView = findViewById(R.id.videoView)
        videoView.setVideoPath("android.resource://" + packageName + "/" + R.raw.splash2)
        videoView.start()
        Handler(Looper.getMainLooper()).postDelayed(
            {
                val i= Intent(this, LoginActivity::class.java)
                startActivity((i))
                finish()},5000)
    }
    }

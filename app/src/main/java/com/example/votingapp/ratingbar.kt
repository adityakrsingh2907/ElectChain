package com.example.votingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RatingBar

class ratingbar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ratingbar)
        val simpleratingbar=findViewById<RatingBar>(R.id.ratingBar)
    }
}
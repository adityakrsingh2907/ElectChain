package com.example.votingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        var toolbar=findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar);
        toolbar.setTitle("DASHBOARD")
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener{
            super.finish()
        }


        val adminRegister = findViewById<FrameLayout>(R.id.adminRegister)
        adminRegister.setOnClickListener {
            val intent = Intent(this,Register::class.java)
            startActivity(intent)
        }

        val adminShow = findViewById<FrameLayout>(R.id.adminShow)
        adminShow.setOnClickListener {
            val intent = Intent(this, ShowAll::class.java)
            startActivity(intent)
        }
        val adminDelte = findViewById<FrameLayout>(R.id.adminDelete)
        adminDelte.setOnClickListener {
            val intent = Intent(this,DeleteForm::class.java)
            startActivity(intent)
        }

        val adminfaq = findViewById<FrameLayout>(R.id.adminFaq)
        adminfaq.setOnClickListener {
            val intent = Intent(this,Faq::class.java)
            startActivity(intent)
        }
        val adminsearch = findViewById<FrameLayout>(R.id.adminsearch)
        adminsearch.setOnClickListener {
            val intent = Intent(this,Search::class.java)
            startActivity(intent)
        }

    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar,menu);
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id:Int=item.itemId
        if(id==R.id.settings){
            Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show()
            return true
        }
        else if(id==R.id.info){
                val intent = Intent(this,Faq::class.java)
                startActivity(intent)
            return true
        }
        else if(id==R.id.feed){
                val view = layoutInflater.inflate(R.layout.activity_ratingbar, null)
                var ad = AlertDialog.Builder(this)
                ad.setMessage("Please Give a Rating")
                ad.setView(view)
                ad.setTitle("Rating")
                ad.setPositiveButton("OK") { x, y ->
                    Toast.makeText(this, "Thank you for the Rating", Toast.LENGTH_SHORT).show()
                }
                ad.setNegativeButton("Cancel") { DialogInterface, which ->
                    Toast.makeText(this, "You Pressed Cancel", Toast.LENGTH_SHORT).show()
                }
                ad.create().show()

            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
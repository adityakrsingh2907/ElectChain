package com.example.votingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class LoginActivity : AppCompatActivity() {
    lateinit var vt1: Voters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activitylogin)

        val et1 = findViewById<EditText>(R.id.emailLogin)
        val et2 = findViewById<EditText>(R.id.password)
        val btn = findViewById<Button>(R.id.login)

//        val btn2 = findViewById<TextView>(R.id.signupText)
//        btn2.setOnClickListener {
//            val intent = Intent(this,registerorm::class.java)
//            startActivity(intent)
//        }

        btn.setOnClickListener {
            if (et1.text.toString().isNotEmpty() && et2.text.toString().isNotEmpty()) {
                if (et1.text.toString() == "Admin" && et2.text.toString() == "8318") {
                    val intent = Intent(this, MainPage::class.java)
                    startActivity(intent)
                }

            } else {
                Toast.makeText(this, "Please Enter Data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() {
        var ad= AlertDialog.Builder(this)
        val view=layoutInflater.inflate(R.layout.exitconfirmation,null)
        ad.setView(view)
        ad.setTitle("Are you Sure ?")
        ad.setPositiveButton("Yes"){x, y ->
           super.finish()
        }
        ad.setNegativeButton("NO"){DialogInterface, which ->
            Toast.makeText(this,"Thanks For Staying !!",Toast.LENGTH_SHORT).show()
        }

        ad.setNeutralButton("Cancel",null)
        ad.create().show()
    }


}

//    private suspend fun giveToast() {
//        withContext(Dispatchers.Main) {
//            toastt()
//        }
//    }
//
//    private fun toastt() {
//        Toast.makeText(this, "No student found", Toast.LENGTH_SHORT).show()
//    }
//}


//    private suspend fun setSt1(st: Voters) {
//        withContext(Dispatchers.Main){
//            setst1(st)
//        }
//    }

//    private fun setst1(vt: Voters) {
//        vt1 = vt
//        val fname = vt1.firstname
//        val lname = vt1.lastname
//        val aadhar = vt1.aadharno
//        val dob = vt1.dob
//        val gender = vt1.gender
//        val state = vt1.state
//
//        val intent = Intent(this,FirstScreen::class.java)
//        intent.putExtra("name",name)
//        intent.putExtra("reg",reg)
//        intent.putExtra("course",course)
//        intent.putExtra("year",year)
//        intent.putExtra("gender",gender)
//        intent.putExtra("address",address)
//        intent.putExtra("cgpa",cgpa)
//        startActivity(intent)
//    }

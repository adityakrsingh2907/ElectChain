package com.example.votingapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Search : AppCompatActivity() {
    lateinit var voterDb : VotersDatabase
    lateinit var viewModel: VoterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        voterDb = VotersDatabase.getDatabase(this)
        val et1 = findViewById<EditText>(R.id.updateid)
        val show = findViewById<Button>(R.id.show)

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(VoterViewModel::class.java)
        show.setOnClickListener {
            if(et1.text.toString().isNotEmpty()){
                GlobalScope.launch {
                    val cl = viewModel.findCount(et1.text.toString().toInt())
                    if (cl != 0) {
                        val vt = voterDb. getVoterDao().findById2(et1.text.toString().toInt())!!
                        showview(vt)
                    }else{
                        giveToast()
                    }
                }
            }else{
                Toast.makeText(this, "Enter Voter ID", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private suspend fun showview(st:Voters) {
        withContext(Dispatchers.Main){
            sho(st)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun sho(vt: Voters) {
        val lin = findViewById<LinearLayout>(R.id.lin2)
        lin.visibility = View.VISIBLE
        val Name = findViewById<TextView>(R.id.Name)
        val aadhar = findViewById<TextView>(R.id.ano)
        val Gender = findViewById<TextView>(R.id.gender)
        val dob = findViewById<TextView>(R.id.dob)
        val state = findViewById<TextView>(R.id.state)
        val id = findViewById<TextView>(R.id.vid)

        Name.text ="Name :      "+ vt.firstname+" "+vt.lastname
        id.text = "Voter ID :    "+ vt.voterid
        aadhar.text = "Aadhar Number : "+ vt.aadharno
        Gender.text = "Gender:     "+ vt.gender
        dob.text = "Date of Birth :    "+ vt.dob
        state.text = "State :   "+ vt.state
    }

    private suspend fun giveToast() {
        withContext(Dispatchers.Main){
            tos()
        }
    }

    private fun tos() {
        Toast.makeText(this, "No Voter Found", Toast.LENGTH_SHORT).show()
        val lin = findViewById<LinearLayout>(R.id.lin2)
        lin.visibility = View.GONE
    }
}
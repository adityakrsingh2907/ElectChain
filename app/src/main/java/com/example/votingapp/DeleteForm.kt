package com.example.votingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DeleteForm : AppCompatActivity() {
    lateinit var VoterDb : VotersDatabase
    lateinit var viewModel: VoterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_form)

        VoterDb = VotersDatabase.getDatabase(this)
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(VoterViewModel::class.java)

        val et1 = findViewById<EditText>(R.id.deleteEt)

        val btn = findViewById<Button>(R.id.deleteDone)

        btn.setOnClickListener {
            if(et1.text.toString().isNotEmpty()){
                if(et1.text.toString().isNotEmpty()){
                    GlobalScope.launch {
                        val cl = viewModel.findCount(et1.text.toString().toInt())
                        if (cl != 0) {
                            val st = VoterDb. getVoterDao().findById2(et1.text.toString().toInt())!!
                            showview(st)
                        }else{
                            giveToast()
                        }
                    }
                }else{
                    Toast.makeText(this, "Enter Reg No", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Enter Data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private suspend fun showview(st: Voters) {
        withContext(Dispatchers.Main){
            alertDialog(st)
        }
    }

    private fun alertDialog(vt: Voters) {
        val et1 = findViewById<EditText>(R.id.deleteEt).text.toString()
        val details = "Name: ${vt.firstname+vt.lastname} \nReg no: ${vt.voterid}"
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Do Yo Want To Delete Voter?")
        builder.setMessage(details)
        builder.setPositiveButton("OK") { dialog, which ->
            // handle OK button click
            viewModel.deleteOne(et1.toInt())
            Toast.makeText(this, "Deleted Successfully", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Cancel") { dialog, which ->
            // handle Cancel button click
            Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show()
        }
        val dialog = builder.create()
        dialog.show()
    }

    private suspend fun giveToast() {
        withContext(Dispatchers.Main){
            tos()
        }
    }

    private fun tos() {
        Toast.makeText(this, "No Voter Found", Toast.LENGTH_SHORT).show()
    }
}
package com.example.votingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ShowAll : AppCompatActivity() {

    lateinit var viewModel: VoterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_all)

        val rcv = findViewById<RecyclerView>(R.id.rcv)

        rcv.layoutManager = LinearLayoutManager(this)
        val adapter = VoterRVAdapter(this)
        rcv.adapter = adapter

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(VoterViewModel::class.java)

        viewModel.allVoter.observe(this, Observer { list->
            list?.let{
                adapter.updateList(it)
            }
        })
    }
}
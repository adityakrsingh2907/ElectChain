package com.example.votingapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.content.Context
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VoterRVAdapter(private val context: Context):RecyclerView.Adapter<VoterRVAdapter.StudentViewHolder>() {

    private val allStudent = ArrayList<Voters>()

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView = itemView.findViewById<TextView>(R.id.vname)
        val vi = itemView.findViewById<TextView>(R.id.vid)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):StudentViewHolder {
        val viewHolder = StudentViewHolder(LayoutInflater.from(context).inflate(R.layout.item_voter,parent,false))
        return viewHolder
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: VoterRVAdapter.StudentViewHolder, position: Int) {
        val currentStudent = allStudent[position]
        holder.textView.text = "Name : " +currentStudent.firstname+" "+currentStudent.lastname
        holder.vi.text = "Voter ID : "+currentStudent.voterid
    }

    override fun getItemCount(): Int {
        return allStudent.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Voters>){
        allStudent.clear()
        allStudent.addAll(newList)
        notifyDataSetChanged()
    }

}
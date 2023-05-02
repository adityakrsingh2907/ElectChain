package com.example.votingapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Update
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VoterViewModel(application: Application):AndroidViewModel(application) {

    val allVoter : LiveData<List<Voters>>

    private val repository:VoterRepository

    init{
        val dao = VotersDatabase.getDatabase(application).getVoterDao()
        repository = VoterRepository(dao)
        allVoter = repository.allVoter

    }

    fun insertStudent(voter: Voters) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(voter)
    }
//    fun deleteStudent(student: Student) = viewModelScope.launch(Dispatchers.IO) {
//        repository.delete(student)
//    }

    fun Update(msg:String,ls:String,g:String,a:String,no:Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateOne(msg,ls,g,a,no)
    }

    fun deleteOne(voterid:Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteOne(voterid)
    }

    suspend fun findVoter(fs:String, ls:String):Int?{
        return repository.getvoter(fs,ls)
    }
    suspend fun findCount(msg:Int):Int?{
        return repository.getcount(msg)
    }


}
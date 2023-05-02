package com.example.votingapp
import androidx.lifecycle.LiveData

class VoterRepository(private val VoterDao : VoterDao) {

    val allVoter: LiveData<List<Voters>> = VoterDao.getAllVoters()

    suspend fun insert(voter: Voters){
        VoterDao.insert(voter)
    }

//    suspend fun delete(voter: Voters){
//        VoterDao.delete(voter)
//    }


    suspend fun updateOne(msg:String,ls:String,g:String,a:String,id:Int){
    VoterDao.update(msg,ls,g,a,id)
    }

    suspend fun deleteOne(voterid:Int):Int?{
        return VoterDao.deleteOne(voterid)
    }

    suspend fun getvoter(msg:String,no:String): Int? {
        return VoterDao.findByName(msg, no)
    }

    suspend fun getById(no:Int):Voters?{
        return VoterDao.findById2(no)
    }
    suspend fun getcount(msg:Int):Int?{
        return VoterDao.countVoter(msg)
    }


//    suspend fun delete(voter:Voters){
//        return VoterDao.deleteAll(voter)
//    }


}
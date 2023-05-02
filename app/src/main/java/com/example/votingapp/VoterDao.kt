package com.example.votingapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface VoterDao {


    @Query("SELECT * FROM voter_table ORDER BY voterid ASC")
    fun getAllVoters(): LiveData<List<Voters>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(voter:Voters)

//    @Delete
//    suspend fun delete(voter:Voters)

    @Query("DELETE FROM voter_table WHERE voterid LIKE:voterid ")
    suspend fun deleteOne(voterid: Int):Int?

    @Query("UPDATE voter_table SET firstname=:fs,lastname=:ls,gender=:g, state=:a WHERE voterid LIKE:id")
    suspend fun update(fs:String,ls:String,g:String,a:String,id:Int)

    @Query("SELECT COUNT(*) FROM voter_table WHERE voterid LIKE :msg")
    suspend fun countVoter(msg:Int):Int?

    @Query("SELECT COUNT(*) FROM voter_table WHERE firstname LIKE :fs AND lastname LIKE :ls")
    suspend fun findByName(fs:String,ls:String):Int?

    @Query("SELECT * FROM voter_table WHERE voterid LIKE :no LIMIT 1")
    suspend fun findById2(no:Int) : Voters?

}
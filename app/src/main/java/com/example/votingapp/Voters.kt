package com.example.votingapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "voter_table")
data class Voters(
    @PrimaryKey(autoGenerate = true) val voterid:Int?,
    @ColumnInfo(name = "firstname") val firstname:String?,
    @ColumnInfo(name="lastname") val lastname:String?,
    @ColumnInfo(name="aadharno") val aadharno:Int?,
    @ColumnInfo(name="gender") val gender:String?,
    @ColumnInfo(name="dob") val dob: String?,
@ColumnInfo(name="state") val state:String?
)

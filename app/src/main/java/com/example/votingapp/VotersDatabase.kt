package com.example.votingapp
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Voters::class],version=1)
abstract class VotersDatabase: RoomDatabase() {

    abstract fun getVoterDao():VoterDao

    companion object{
        @Volatile
        private var INSTANCE: VotersDatabase? = null

        fun getDatabase(context: Context): VotersDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    VotersDatabase::class.java,
//                    "student_database"
//                ).build()
//                INSTANCE = instance
//                // return instance
//                instance
//            }
            val tempInstance= INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance=Room.databaseBuilder(
                    context.applicationContext,
                    VotersDatabase::class.java,
                    "voters_databse"
                ).build()
                INSTANCE=instance
                return instance
            }
        }
    }
}
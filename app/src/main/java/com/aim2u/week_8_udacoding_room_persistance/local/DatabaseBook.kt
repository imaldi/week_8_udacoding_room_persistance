package com.aim2u.week_8_udacoding_room_persistance.local


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(LocalBook::class), version = 1)
abstract class DatabaseBook : RoomDatabase(){
    abstract fun bookDao(): DaoBook

    companion object{
        private var INSTANCE : DatabaseBook? = null
        fun getInstance(context: Context):DatabaseBook?{
            if(INSTANCE == null){
                synchronized(DatabaseBook::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        DatabaseBook::class.java, "bookdb.db").fallbackToDestructiveMigration().build()
                }

            }
            return INSTANCE
        }
    }
}
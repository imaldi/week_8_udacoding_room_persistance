package com.aim2u.week_8_udacoding_room_persistance.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(LocalUser::class), version = 2)
abstract class DatabaseUser : RoomDatabase(){
    abstract fun userDao(): DaoUser

    companion object{
        private var INSTANCE : DatabaseUser? = null
        fun getInstance(context: Context):DatabaseUser?{
            if(INSTANCE == null){
//                val MIGRATION_1_2 = object : Migration(1, 2) {
//                    override fun migrate(database: SupportSQLiteDatabase) {
//                        database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, `name` TEXT, " +
//                                "PRIMARY KEY(`id`))")
//                    }
//                }
                synchronized(DatabaseUser::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    DatabaseUser::class.java, "userdb.db").fallbackToDestructiveMigration().build()
                }

            }
            return INSTANCE
        }
    }
}
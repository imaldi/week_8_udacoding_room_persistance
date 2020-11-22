package com.aim2u.week_8_udacoding_room_persistance.local

import androidx.room.*

@Dao
interface DaoUser {
    @Query("SELECT * FROM user")
    fun getAll() : List<LocalUser>

    @Query("SELECT * FROM user WHERE name = :name AND password = :password")
    fun getAnUser(name: String,password : String) : LocalUser

    @Query("SELECT * FROM user WHERE email = :email")
    fun checkUserEmail(email: String) : LocalUser

    @Insert
    fun insert(user : LocalUser)

    @Update
    fun update(user : LocalUser)

    @Delete
    fun delete(user : LocalUser)
}
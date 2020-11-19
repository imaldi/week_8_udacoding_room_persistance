package com.aim2u.week_8_udacoding_room_persistance.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class LocalUser(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name= "id")
    var id:Int? = null,

    @ColumnInfo(name = "name")
    var name:String? = null,

    @ColumnInfo(name = "email")
    var email:String? = null,

    @ColumnInfo(name = "password")
    var password:String? = null
)
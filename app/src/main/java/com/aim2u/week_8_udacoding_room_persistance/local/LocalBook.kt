package com.aim2u.week_8_udacoding_room_persistance.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book")
data class LocalBook(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name= "id")
    var id:Int? = null,

    @ColumnInfo(name = "book_name")
    var book_name:String? = null,

    @ColumnInfo(name = "author")
    var author:String? = null,

    @ColumnInfo(name = "released_year")
    var released_year:String? = null
)
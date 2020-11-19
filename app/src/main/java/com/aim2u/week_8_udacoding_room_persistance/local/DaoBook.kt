package com.aim2u.week_8_udacoding_room_persistance.local

import androidx.room.*
import io.reactivex.rxjava3.core.Single

@Dao
interface DaoBook {
    @Query("SELECT * FROM book")
    fun getAll() : List<LocalBook>

    @Query("SELECT * FROM book WHERE book_name = :book_name")
    fun getABook(book_name: String) : LocalBook

    @Insert
    fun insert(book: LocalBook)

    @Update
    fun update(book: LocalBook)

    @Delete
    fun delete(book: LocalBook)
}
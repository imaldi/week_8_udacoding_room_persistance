package com.aim2u.week_8_udacoding_room_persistance.ui.books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aim2u.week_8_udacoding_room_persistance.local.DatabaseBook
import com.aim2u.week_8_udacoding_room_persistance.local.LocalBook
import com.aim2u.week_8_udacoding_room_persistance.repository.Repository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class BookViewModel : ViewModel() {
    private var repository = Repository()
    var bookList = MutableLiveData<List<LocalBook>?>()

    fun addBook(databaseBook: DatabaseBook?, book: LocalBook, toast : (String)->Unit){
        repository.addBook(databaseBook,book,{
            toast(it)
        },{
            toast(it.message ?: "Ada Suatu Eror")
        })
    }

    fun showBooks(databaseBook: DatabaseBook?, errorHandler : (Throwable)->Unit) {
        repository.showBooks(databaseBook,{
            bookList.value = it
        },{
            errorHandler(it)
        })
    }

    fun updateBook(databaseBook: DatabaseBook?, item : LocalBook, toast : (String)->Unit){
        repository.updateBook(databaseBook,item,{
            toast(it)
        },{
            toast(it.message ?: "Ada Suatu Eror")
        })
    }

    fun deleteBook(databaseBook: DatabaseBook?, item : LocalBook?, toast : (String)->Unit){
        repository.deleteBook(databaseBook,item,{
            toast(it)
        },{
            toast(it.message ?: "Ada Suatu Eror")
        })
    }
}
package com.aim2u.week_8_udacoding_room_persistance.repository

import android.app.AlertDialog
import android.widget.Toast
import com.aim2u.week_8_udacoding_room_persistance.adapter.DataAdapter
import com.aim2u.week_8_udacoding_room_persistance.local.DatabaseBook
import com.aim2u.week_8_udacoding_room_persistance.local.DatabaseUser
import com.aim2u.week_8_udacoding_room_persistance.local.LocalBook
import com.aim2u.week_8_udacoding_room_persistance.local.LocalUser
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_books.*


class Repository {

    fun addUser(databaseUser: DatabaseUser?, user : LocalUser, successHandler : (String)->Unit, errorHandler : (Throwable)->Unit){
        Observable.fromCallable{
            databaseUser?.userDao()?.insert(user)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                successHandler("Sukses Register User")
            },{
                errorHandler(it)
            })
    }

    fun login(databaseUser: DatabaseUser?, username : String, password : String, successHandler : (LocalUser?, String)->Unit, errorHandler : (Throwable)->Unit){
        Observable.fromCallable{
            databaseUser?.userDao()?.getAnUser(username,password)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                successHandler(it,"Sukses Login")
            },{
                errorHandler(it)
            })
    }

    fun addBook(databaseBook: DatabaseBook?, book: LocalBook, successHandler : (String)->Unit, errorHandler : (Throwable)->Unit){
        Observable.fromCallable{ databaseBook?.bookDao()?.insert(book) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                successHandler("Book berhasil disimpan")
            }, {
                errorHandler(it)
            })
    }

    fun showBooks(databaseBook: DatabaseBook?,successHandler : (List<LocalBook>?)->Unit, errorHandler : (Throwable)->Unit) {
        Observable.fromCallable{
            databaseBook?.bookDao()?.getAll()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                successHandler(it)
            }, {
                errorHandler(it)
            })
    }

    fun updateBook(databaseBook: DatabaseBook?, item : LocalBook,successHandler : (String)->Unit, errorHandler : (Throwable)->Unit){
        Observable.fromCallable{
            databaseBook?.bookDao()?.update(item)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                successHandler("Buku berhasil di update")
            }, {
                errorHandler(it)
            })
    }

    fun deleteBook(databaseBook: DatabaseBook?, item : LocalBook?,successHandler : (String)->Unit, errorHandler : (Throwable)->Unit){
        Observable.fromCallable{
            databaseBook?.bookDao()?.delete(item!!)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                successHandler("Buku berhasil dihapus")
            }, {
                errorHandler(it)
            })
    }
}
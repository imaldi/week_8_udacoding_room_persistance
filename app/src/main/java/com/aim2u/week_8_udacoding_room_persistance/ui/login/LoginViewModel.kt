package com.aim2u.week_8_udacoding_room_persistance.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aim2u.week_8_udacoding_room_persistance.local.DatabaseUser
import com.aim2u.week_8_udacoding_room_persistance.local.LocalUser
import com.aim2u.week_8_udacoding_room_persistance.repository.Repository

class LoginViewModel : ViewModel(){
    private val repository = Repository()
    var user = MutableLiveData<LocalUser?>()
    fun login(databaseUser: DatabaseUser?, username:String, passsword:String, toast : (String) -> Unit){
        repository.login(databaseUser,username,passsword,{ userDatabase, message ->
            user.value = userDatabase
            toast(message)
        },{
            if (it.message?.startsWith("Callable returned a null value.")?:false){
                toast("User belum terdaftar atau password salah")
            } else {
                toast(""+it?.message)
            }

        })
    }
}
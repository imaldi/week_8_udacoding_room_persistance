package com.aim2u.week_8_udacoding_room_persistance.ui.register1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aim2u.week_8_udacoding_room_persistance.local.DatabaseUser
import com.aim2u.week_8_udacoding_room_persistance.local.LocalUser
import com.aim2u.week_8_udacoding_room_persistance.repository.Repository

class Register1ViewModel : ViewModel(){
    private val repository = Repository()
    var user = MutableLiveData<LocalUser?>()
    fun checkEmailUser(databaseUser: DatabaseUser?, email : String, nextPage : () -> Unit){
        repository.checkUser(databaseUser,email,{
            user.value = it
        },{
            if (it.message?.startsWith("Callable returned a null value.")?:false) {
                nextPage()
            }
        })
    }
}
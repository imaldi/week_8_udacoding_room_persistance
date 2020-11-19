package com.aim2u.week_8_udacoding_room_persistance.ui.register2

import androidx.lifecycle.ViewModel
import com.aim2u.week_8_udacoding_room_persistance.local.DatabaseUser
import com.aim2u.week_8_udacoding_room_persistance.local.LocalUser
import com.aim2u.week_8_udacoding_room_persistance.repository.Repository

class Register2ViewModel : ViewModel(){
    private val repository = Repository()
    fun addUser(databaseUser: DatabaseUser?, user : LocalUser, toast : (String) -> Unit){
        repository.addUser(databaseUser,user,{
            toast(it)
        },{
            toast(""+it?.message)
        })
    }
}
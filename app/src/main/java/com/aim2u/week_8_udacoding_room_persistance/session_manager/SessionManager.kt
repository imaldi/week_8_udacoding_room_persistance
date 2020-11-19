package com.aim2u.week_8_udacoding_room_persistance.session_manager

import android.content.Context
import android.content.SharedPreferences
import com.aim2u.week_8_udacoding_room_persistance.R

class SessionManager (context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
    companion object{
        const val USER_LOGIN = "user_login"
        const val USER_NAME = "user_name"
    }

    fun saveSession(session : Boolean){
        val editor = prefs.edit()
        editor.putBoolean(USER_LOGIN, session)
        editor.apply()
    }

    fun fetchSession(): Boolean? {
        return prefs.getBoolean(USER_LOGIN, false)
    }

    fun saveUsername(username : String){
        val editor = prefs.edit()
        editor.putString(USER_NAME, username)
        editor.apply()
    }

    fun fetchUsername(): String? {
        return prefs.getString(USER_NAME, null)
    }

    fun clearAllSharedPreferences(){
        val editor : SharedPreferences.Editor = prefs.edit()

        editor.clear()
        editor.apply()
    }
}
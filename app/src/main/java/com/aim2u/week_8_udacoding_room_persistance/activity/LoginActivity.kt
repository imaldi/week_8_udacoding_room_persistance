package com.aim2u.week_8_udacoding_room_persistance.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aim2u.week_8_udacoding_room_persistance.R
import com.aim2u.week_8_udacoding_room_persistance.session_manager.SessionManager

class LoginActivity : AppCompatActivity() {
//    private lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

//    override fun onResume() {
//        super.onResume()
//        val sessionManager = SessionManager(this)
//        if (sessionManager.fetchSession() != false){
//            startActivity(Intent(this,MainActivity::class.java))
//        }
//    }
}
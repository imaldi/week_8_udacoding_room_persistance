package com.aim2u.week_8_udacoding_room_persistance.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.aim2u.week_8_udacoding_room_persistance.R
import com.aim2u.week_8_udacoding_room_persistance.activity.MainActivity
import com.aim2u.week_8_udacoding_room_persistance.local.DatabaseUser
import com.aim2u.week_8_udacoding_room_persistance.session_manager.SessionManager
import kotlinx.android.synthetic.main.fragment_login.*



/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    private lateinit var databaseUser : DatabaseUser
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var sessionManager: SessionManager


    override fun onResume() {
        super.onResume()
        val sessionManager = SessionManager(requireContext())
        if (sessionManager.fetchSession() != null){
            activity?.finishAffinity()
            startActivity(Intent(requireContext(),MainActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databaseUser = DatabaseUser.getInstance(requireContext())!!
        sessionManager = SessionManager(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        
        btn_register.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_register1Fragment)
        }

        btn_login.setOnClickListener {
            loginViewModel.login(
                databaseUser,
                editTextTextUsername.text.toString(),
                editTextTextPassword.text.toString()
            ) {
                Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
                if (it != "User belum terdaftar atau password salah") {
                    sessionManager.saveSession("In")
                    sessionManager.saveUsername(editTextTextUsername.text.toString())
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    activity?.finishAffinity()
                    startActivity(intent)
                }
            }

        }
        loginViewModel.user.observe(viewLifecycleOwner){
            if (it != null){
//                Toast.makeText(requireContext(), "${it.name}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
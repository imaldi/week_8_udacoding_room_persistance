package com.aim2u.week_8_udacoding_room_persistance.ui.register1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.aim2u.week_8_udacoding_room_persistance.R
import com.aim2u.week_8_udacoding_room_persistance.local.DatabaseUser
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register1.*
import kotlinx.android.synthetic.main.fragment_register1.btn_next

class Register1Fragment : Fragment() {

    private lateinit var register1ViewModel: Register1ViewModel
    private lateinit var databaseUser : DatabaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databaseUser = DatabaseUser.getInstance(requireContext())!!
        register1ViewModel = ViewModelProviders.of(this).get(Register1ViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        register1ViewModel.user.observe(viewLifecycleOwner){
            if (it != null){
                Toast.makeText(requireContext(), "User dengan email ini sudah terdaftar.", Toast.LENGTH_SHORT).show()
            }
        }
        text_register1.setOnClickListener {
            activity?.onBackPressed()
        }
        btn_next.setOnClickListener {
            if(editTextRegisterUsername.text.toString().isEmpty()){
                editTextRegisterUsername.error = "Nama Harus Diisi"
            } else if (editTextEmail.text.toString().isEmpty()){
                editTextEmail.error = "Email Harus Diisi"
            } else {
                register1ViewModel.checkEmailUser(databaseUser, editTextEmail.text.toString()){
                    emailUserBelumTerdaftar()
                }
            }
        }
    }

    fun emailUserBelumTerdaftar(){
        val usernameAndEmailBundle = bundleOf(
            "username" to editTextRegisterUsername.text.toString(),
            "email" to editTextEmail.text.toString()
        )
        findNavController().navigate(
            R.id.action_register1Fragment_to_register2Fragment,
            usernameAndEmailBundle
        )
    }

}
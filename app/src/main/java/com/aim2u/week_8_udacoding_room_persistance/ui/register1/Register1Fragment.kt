package com.aim2u.week_8_udacoding_room_persistance.ui.register1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.aim2u.week_8_udacoding_room_persistance.R
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register1.*
import kotlinx.android.synthetic.main.fragment_register1.btn_next

class Register1Fragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        text_register1.setOnClickListener {
            activity?.onBackPressed()
        }
        btn_next.setOnClickListener {
            if(editTextRegisterUsername.text.toString().isEmpty()){
                editTextRegisterUsername.error = "Nama Harus Diisi"
            } else if (editTextEmail.text.toString().isEmpty()){
                editTextEmail.error = "Email Harus Diisi"
            } else {
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
    }

}
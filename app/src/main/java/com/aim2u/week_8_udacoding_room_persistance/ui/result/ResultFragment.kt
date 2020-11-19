package com.aim2u.week_8_udacoding_room_persistance.ui.result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.aim2u.week_8_udacoding_room_persistance.R
import kotlinx.android.synthetic.main.fragment_result.*

class ResultFragment : Fragment() {
    var get_name : String? = null
    var get_email : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        get_name = arguments?.getString("username")
        get_email = arguments?.getString("email")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_username_result.text = get_name
        tv_email_result.text = get_email
        btn_back_to_login.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_loginFragment)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

}
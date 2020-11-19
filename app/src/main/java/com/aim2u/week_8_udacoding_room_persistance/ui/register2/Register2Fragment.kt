package com.aim2u.week_8_udacoding_room_persistance.ui.register2

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
import com.aim2u.week_8_udacoding_room_persistance.local.LocalUser
import kotlinx.android.synthetic.main.fragment_register2.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Register2Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Register2Fragment : Fragment() {
    var get_name : String? = null
    var get_email : String? = null
    private var databaseUser : DatabaseUser? = null
    private var register2ViewModel : Register2ViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        get_name = arguments?.getString("username")
        get_email = arguments?.getString("email")
        register2ViewModel = ViewModelProviders.of(this).get(Register2ViewModel::class.java)
        databaseUser = DatabaseUser.getInstance(requireContext())


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tv_welcome_register.text = "Selamat Datang $get_name, silahkan buat password baru"
        text_register2.setOnClickListener {
            activity?.onBackPressed()
        }
        btn_confirm_register.setOnClickListener {
            if(editTextNewPassword.text.toString().isEmpty()){
                editTextNewPassword.error = "Password Harus Diisi"
            } else if (editTextPasswordConfirmation.text.toString().isEmpty()){
                editTextPasswordConfirmation.error = "Confirmation Password Harus Diisi"
            } else {
                if (editTextNewPassword.text.toString().equals(editTextPasswordConfirmation.text.toString())) {
                    val bundle = bundleOf(
                        "username" to get_name,
                        "email" to get_email,
                        "password" to editTextNewPassword.text.toString()
                    )
                    register2ViewModel?.addUser(
                        databaseUser,
                        LocalUser(null, get_name, get_email, editTextNewPassword.text.toString())
                    ) {
                        Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
                    }
                    findNavController().navigate(R.id.action_register2Fragment_to_resultFragment,bundle)
                }else{
                    Toast.makeText(requireContext(), "Password tidak sama dengan Confirmation Password", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
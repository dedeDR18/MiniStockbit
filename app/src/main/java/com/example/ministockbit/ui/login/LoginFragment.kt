package com.example.ministockbit.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.ministockbit.MainActivity
import com.example.ministockbit.R
import com.google.android.material.button.MaterialButton

class LoginFragment : Fragment() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                    requireActivity().finish()
            }

        })
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

        navController = Navigation.findNavController(view)
        val btnLogin = view.findViewById<MaterialButton>(R.id.btn_login)

        btnLogin.setOnClickListener {
            navController!!.navigate(R.id.action_nav_login_to_nav_watchlist)
        }
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as MainActivity).bottomNavView.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).bottomNavView.visibility = View.GONE
    }



}
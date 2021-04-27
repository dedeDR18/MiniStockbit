package com.example.ministockbit.ui.login

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.ministockbit.MainActivity
import android.text.Spanned;
import android.util.Log
import android.widget.Toast
import com.example.ministockbit.R
import com.example.ministockbit.databinding.FragmentLoginBinding
import com.google.android.material.button.MaterialButton

class LoginFragment : Fragment() {

    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        binding.btnLogin.setOnClickListener {
            navController.navigate(R.id.action_nav_login_to_nav_watchlist)
        }

        binding.btnLoginFacebook.setOnClickListener{
            Toast.makeText(requireContext(), getString(R.string.text_toast_facebook_button), Toast.LENGTH_SHORT).show()
        }

        binding.btnLoginGoogle.setOnClickListener{
            Toast.makeText(requireContext(), getString(R.string.text_toast_google_button), Toast.LENGTH_SHORT).show()
        }

        binding.textButtonForgetPassword.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.text_toast_forget_password_button), Toast.LENGTH_SHORT).show()
        }

        binding.btnFingerPrint.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.text_toast_fingerprint_button), Toast.LENGTH_SHORT).show()
        }

        val spannable = SpannableString(getString(R.string.text_sign_up_button))
        spannable.setSpan(ForegroundColorSpan(requireActivity().getResources().getColor(R.color.green_stockbit)), 18, 33, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.textButtonSignin.apply {
            text = spannable
            setOnClickListener {
                Toast.makeText(requireContext(), context.getString(R.string.text_toast_sign_up_button), Toast.LENGTH_SHORT).show()
            }
        }


    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as MainActivity).setBottomNavViewVisibility(true)
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).setBottomNavViewVisibility(false)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



}
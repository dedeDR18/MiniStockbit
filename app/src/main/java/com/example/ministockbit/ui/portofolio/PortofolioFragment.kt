package com.example.ministockbit.ui.portofolio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ministockbit.R
import com.example.ministockbit.databinding.FragmentPortofolioBinding


class PortofolioFragment : Fragment() {

    private var _binding : FragmentPortofolioBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPortofolioBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
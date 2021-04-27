package com.example.ministockbit.ui.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ministockbit.R
import com.example.ministockbit.databinding.FragmentWatchlistBinding

class WatchlistFragment : Fragment() {

    private lateinit var watchlistViewModel: WatchlistViewModel
    private var _binding : FragmentWatchlistBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        watchlistViewModel = ViewModelProvider(this).get(WatchlistViewModel::class.java)
        _binding = FragmentWatchlistBinding.inflate(inflater, container, false)
        val view = binding.root

        watchlistViewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textWacthlist.text = it
        })

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
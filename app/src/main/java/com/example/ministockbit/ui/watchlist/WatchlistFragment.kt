package com.example.ministockbit.ui.watchlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.core.data.Resource
import com.example.ministockbit.R
import com.example.ministockbit.databinding.FragmentWatchlistBinding
import org.koin.android.viewmodel.ext.android.viewModel


class WatchlistFragment : Fragment() {

    private val watchlistViewModel: WatchlistViewModel by viewModel()
    private var _binding : FragmentWatchlistBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWatchlistBinding.inflate(inflater, container, false)
        val view = binding.root

        watchlistViewModel.text.observe(requireActivity(), Observer {
            binding.textWacthlist.text = it
        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        watchlistViewModel.dataCrypto.observe(viewLifecycleOwner, { dataCrypto ->
            dataCrypto?.let { data ->
                when(data){
                    is Resource.Loading -> Log.d("WATCHLISTFRAGMENT", "loading....")
                    is Resource.Success -> {
                        Log.d("WATCHLISTFRAGMENT", "Adanih Datanya... ${data.data!![0].coinName}")
                    }
                    is Resource.Error -> {
                        Toast.makeText(requireContext(), "error nih", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
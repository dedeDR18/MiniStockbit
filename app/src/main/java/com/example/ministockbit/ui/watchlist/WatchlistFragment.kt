package com.example.ministockbit.ui.watchlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.core.data.Resource
import com.example.ministockbit.databinding.FragmentWatchlistBinding
import com.example.ministockbit.ui.watchlist.adapter.CryptoAdapter
import org.koin.android.viewmodel.ext.android.viewModel


class WatchlistFragment : Fragment() {

    private var _binding : FragmentWatchlistBinding? = null
    private val binding get() = _binding!!
    private val watchlistViewModel: WatchlistViewModel by viewModel()
    private lateinit var adapterCrypto: CryptoAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWatchlistBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterCrypto = CryptoAdapter()

        watchlistViewModel.dataCrypto.observe(viewLifecycleOwner, { dataCrypto ->
            dataCrypto?.let { data ->
                when(data){
                    is Resource.Loading -> showLoading(true)
                    is Resource.Success -> {
                        Log.d("WATCHLISTFRAGMENT", "Adanih Datanya...")
                        adapterCrypto.setData(data.data)
                        showLoading(false)
                    }
                    is Resource.Error -> {
                        showLoading(false)
                        Toast.makeText(requireContext(), "error nih", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        initRv()
    }

    fun showLoading(state: Boolean) = binding.pbCrypto.apply {
        visibility = if(state) View.VISIBLE else View.GONE
    }

    fun initRv() = binding.rvCrypto.apply {
        layoutManager = LinearLayoutManager(requireContext())
        val divider = DividerItemDecoration(requireContext(), (layoutManager as LinearLayoutManager).orientation)
        setHasFixedSize(true)
        addItemDecoration(divider)
        adapter = adapterCrypto

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
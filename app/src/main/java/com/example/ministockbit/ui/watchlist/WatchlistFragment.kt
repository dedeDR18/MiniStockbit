package com.example.ministockbit.ui.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.data.Resource
import com.example.ministockbit.R
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

        initSwipeToRefresh()
        observe()
        initRv()

    }

    private fun observe(){
        watchlistViewModel.dataCrypto.observe(viewLifecycleOwner, { dataCrypto ->
            dataCrypto?.let { data ->
                when(data){
                    is Resource.Loading -> showRefresh(true)
                    is Resource.Success -> {
                        adapterCrypto.setData(data.data)
                        showRefresh(false)
                    }
                    is Resource.Error -> {
                        showRefresh(false)
                        Toast.makeText(requireContext(), getString(R.string.on_error), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun showRefresh(state: Boolean) = binding.swiperefreshLayout.apply {
        isRefreshing = state
    }

    private fun initSwipeToRefresh() = binding.swiperefreshLayout.apply {
        setOnRefreshListener {
            isRefreshing = true
            observe()
        }
    }

    private fun initRv() = binding.rvCrypto.apply {
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
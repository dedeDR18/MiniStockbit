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

class WatchlistFragment : Fragment() {

    private lateinit var watchlistViewModel: WatchlistViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        watchlistViewModel =
                ViewModelProvider(this).get(WatchlistViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_watchlist, container, false)
        val textView: TextView = root.findViewById(R.id.text_wacthlist)
        watchlistViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

}
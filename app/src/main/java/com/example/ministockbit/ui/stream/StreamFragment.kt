package com.example.ministockbit.ui.stream

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.ministockbit.databinding.FragmentStreamBinding
import org.koin.android.viewmodel.ext.android.viewModel

class StreamFragment : Fragment() {

    private var _binding : FragmentStreamBinding? = null
    private val binding get() = _binding!!
    private val vm: StreamViewModel by viewModel()


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentStreamBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.stocks.observe(viewLifecycleOwner, Observer {
            when(it.FROMSYMBOL){
                "BTC" -> binding.tvPriceBtc.text = "US $ ".plus(it.PRICE.toString())
                "LTC" -> binding.tvPriceLtc.text = "US $ ".plus(it.PRICE.toString())
            }
        Log.d("WEBSOCKET", "RESPONSE =  ${it.PRICE}")
    })
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
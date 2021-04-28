package com.example.ministockbit.ui.watchlist.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.Crypto
import com.example.ministockbit.R
import com.example.ministockbit.databinding.ItemListCryptoBinding

/**
 * Created on : 28/04/21 | 10.34
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

class CryptoAdapter : RecyclerView.Adapter<CryptoAdapter.ListCryptoViewHolder>() {

    private var listData = ArrayList<Crypto>()


    fun setData(data: List<Crypto>?){
        if (data!!.isNotEmpty()){
            listData.clear()
            listData.addAll(data)
            notifyDataSetChanged()
            Log.d("data", "jumlah = ${itemCount}")
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCryptoViewHolder =
        ListCryptoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_crypto, parent, false))

    override fun onBindViewHolder(holder: ListCryptoViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount() = listData.size

    inner class ListCryptoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListCryptoBinding.bind(itemView)
        fun bind(data: Crypto){
            with(binding){
                textName.text = data.coinName
                textFullname.text = data.coinFullname
                textPrice.text = data.coinPrice
                textAlgorithm.text = data.coinAlgorithm
            }
        }
    }
}
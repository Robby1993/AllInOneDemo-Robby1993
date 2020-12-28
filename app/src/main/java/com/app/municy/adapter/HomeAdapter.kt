package com.app.municy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.municy.R
import com.app.municy.databinding.ItemAgencyBinding
import com.app.municy.databinding.ItemHomeBinding
import com.app.municy.model.Notification

class HomeAdapter(var context: Context, var data: MutableList<Notification>) :
    RecyclerView.Adapter<HomeAdapter.WalletViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletViewHolder {
        return WalletViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_home, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: WalletViewHolder, position: Int) {
        val transaction = data[position]
        holder.binding.tvAddress.text = transaction.description
    }

    class WalletViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: ItemHomeBinding = ItemHomeBinding.bind(itemView)
    }
}
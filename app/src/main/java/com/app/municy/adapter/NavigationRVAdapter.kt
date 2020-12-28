package com.app.municy.adapter

import NavigationItemModel
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.app.municy.R
import com.app.municy.databinding.RowNavDrawerBinding

class NavigationRVAdapter(
    private var items: ArrayList<NavigationItemModel>,
    private var currentPos: Int
) : RecyclerView.Adapter<NavigationRVAdapter.NavigationItemViewHolder>() {
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationItemViewHolder {
        context = parent.context
        val navItem =
            LayoutInflater.from(parent.context).inflate(R.layout.row_nav_drawer, parent, false)
        return NavigationItemViewHolder(navItem)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: NavigationItemViewHolder, position: Int) {
        // To highlight the selected Item, show different background color
        if (position == currentPos) {
            //holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
            holder.itemView.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.transparentb
                )
            )
            holder.binding.navigationTitle.setTextColor(Color.WHITE)
        } else {
            holder.itemView.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    android.R.color.transparent
                )
            )
            holder.binding.navigationTitle.setTextColor(Color.BLACK)
        }
        holder.binding.navigationTitle.text = items[position].title
    }

    class NavigationItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: RowNavDrawerBinding = RowNavDrawerBinding.bind(itemView)
    }

}
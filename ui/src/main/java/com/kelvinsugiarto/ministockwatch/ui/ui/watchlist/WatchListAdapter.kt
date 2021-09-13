package com.kelvinsugiarto.ministockwatch.ui.ui.watchlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kelvinsugiarto.ministockwatch.data.source.model.CryptoModelEnt
import com.kelvinsugiarto.ministockwatch.ui.R
import com.kelvinsugiarto.ministockwatch.ui.utils.addPrefix
import com.kelvinsugiarto.ministockwatch.ui.utils.changeTextColor
import com.kelvinsugiarto.ministockwatch.ui.utils.formatPrice
import com.kelvinsugiarto.ministockwatch.ui.utils.formatPriceChanges


class WatchListAdapter(
    private val onBottomReached: () -> Unit
) : ListAdapter<CryptoModelEnt, RecyclerView.ViewHolder>(CryptoItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewLayout = LayoutInflater.from(parent.context).inflate(R.layout.watch_list_item,parent,false)
        return ItemViewHolder(viewLayout)
    }

    override fun submitList(list: List<CryptoModelEnt>?) {
        super.submitList(list)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> holder.bind(getItem(position))
        }
        if (position == itemCount - 1) {
            onBottomReached()
        }
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewCode: TextView = itemView.findViewById(R.id.text_view_code)
        var textViewCompanyName:TextView = itemView.findViewById(R.id.text_view_company_name)
        var textViewPriceChange:TextView  = itemView.findViewById(R.id.text_view_price_change)
        var textViewPrice:TextView = itemView.findViewById(R.id.text_view_price)


        fun bind(cryptoModel: CryptoModelEnt) {
            textViewCode.text = cryptoModel.name
            textViewCompanyName.text = cryptoModel.fullName
            textViewPriceChange.changeTextColor(cryptoModel.changePrice, itemView.context)
            textViewPrice.text = formatPrice(cryptoModel.currentPrice)
            val changePercentage = formatPriceChanges(cryptoModel.changePricePercentage).addPrefix()
            val changePrice =
                formatPriceChanges(cryptoModel.changePrice).addPrefix()
            textViewPriceChange.text =
                itemView.context.getString(R.string.price_change_format, changePrice, changePercentage)
        }
    }

    class CryptoItemDiffCallback : DiffUtil.ItemCallback<CryptoModelEnt>() {
        override fun areItemsTheSame(oldItem: CryptoModelEnt, newItem: CryptoModelEnt): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CryptoModelEnt, newItem: CryptoModelEnt): Boolean {
            return oldItem == newItem
        }
    }
}

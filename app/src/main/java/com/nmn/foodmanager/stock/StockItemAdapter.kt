package com.nmn.foodmanager.stock

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.nmn.foodmanager.databinding.RecyclerviewStockItemBinding
import com.nmn.foodmanager.main.entity.StockItem
import java.time.LocalDate

class StockItemAdapter(private val onDeleteCallback: (StockItem) -> Unit, private val showPopUpUpdateItem: (StockItem) -> Unit) : RecyclerView.Adapter<StockItemAdapter.ViewHolder>() {

    private var stockItemList = emptyList<StockItem>()

    inner class ViewHolder(val binding: RecyclerviewStockItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("StockItemAdapter", "CreateViewHolder: $parent / $viewType")
        val binding = RecyclerviewStockItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = stockItemList[position]
        val expirationDate = LocalDate.ofEpochDay(currentItem.expirationDate)
        Log.d("StockItemAdapter", "Current item: $currentItem")
        holder.binding.textViewStockItemName.text = currentItem.name
        holder.binding.textViewStockItemQuantity.text = currentItem.quantity.toString()
        holder.binding.textViewStockItemExpireDate.text = expirationDate.toString()
        holder.binding.supprStockElement.setOnClickListener {
            onDeleteCallback(currentItem)
        }
        holder.binding.stockItem.setOnClickListener {
            showPopUpUpdateItem(currentItem)
        }
    }

    override fun getItemCount(): Int {
        val itemCount = stockItemList.size
        Log.d("StockItemAdapter", "getItemCount : $itemCount")
        return itemCount
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(stockItemList: List<StockItem>) {
        Log.d("StockItemAdapter", "Set Data: $stockItemList")
        this.stockItemList = stockItemList
        notifyDataSetChanged()
    }
}
package com.nmn.foodmanager.stock

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nmn.foodmanager.databinding.RecyclerviewStockItemBinding
import com.nmn.foodmanager.main.entity.StockItem

class StockItemAdapter(private val onDeleteCallback: (StockItem) -> Unit) : RecyclerView.Adapter<StockItemAdapter.ViewHolder>() {

    private var stockItemList = emptyList<StockItem>()

    inner class ViewHolder(val binding: RecyclerviewStockItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("StockItemAdapter", "CreateViewHolder: $parent / $viewType")
        val binding = RecyclerviewStockItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var currentItem = stockItemList[position]
        Log.d("StockItemAdapter", "Current item: $currentItem")
        holder.binding.textViewShopListItemName.text = currentItem.name
        holder.binding.textViewStockItemQuantity.text = currentItem.quantity.toString()
        holder.binding.textViewStockItemExpireDate.text = currentItem.expirationDate.toString()
//        holder.binding.validate.setOnCheckedChangeListener { buttonView, isChecked ->
//            Log.d("ShoppingListItemAdapterCheckBox", "$currentItem is checked: $isChecked")
//            if(isChecked) {
//                currentItem.status = true
//            }
//            else {
//                currentItem.status = false
//            }
//            onUpdateCallback(currentItem)
//        }
        holder.binding.supprStockElement.setOnClickListener {
            onDeleteCallback(currentItem)
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
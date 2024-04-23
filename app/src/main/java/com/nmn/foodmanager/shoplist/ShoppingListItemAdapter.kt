package com.nmn.foodmanager.shoplist

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nmn.foodmanager.databinding.RecyclerviewShoppingListItemBinding
import com.nmn.foodmanager.main.entity.ShoppingListItem

class ShoppingListItemAdapter(private val onDeleteCallback: (ShoppingListItem) -> Unit, private val onUpdateCallback: (ShoppingListItem) -> Unit) : RecyclerView.Adapter<ShoppingListItemAdapter.ViewHolder>() {

    private var shoppingList = emptyList<ShoppingListItem>()

    inner class ViewHolder(val binding: RecyclerviewShoppingListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("ShoppingListItemAdapter", "CreateViewHolder: $parent / $viewType")
        val binding = RecyclerviewShoppingListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var currentItem = shoppingList[position]
        Log.d("ShoppingListItemAdapter", "Current item: $currentItem")
        holder.binding.textViewShopListItemName.text = currentItem.name
        holder.binding.textViewShopListItemQuantity.text = currentItem.quantity.toString()
        holder.binding.validate.isChecked = currentItem.status
        holder.binding.validate.setOnCheckedChangeListener { buttonView, isChecked ->
            Log.d("ShoppingListItemAdapterCheckBox", "$currentItem is checked: $isChecked")
            currentItem.status = isChecked
            onUpdateCallback(currentItem)
        }
        holder.binding.supprElement.setOnClickListener {
            onDeleteCallback(currentItem)
        }
    }

    override fun getItemCount(): Int {
        val itemCount = shoppingList.size
        Log.d("ShoppingListItemAdapter", "getItemCount : $itemCount")
        return itemCount
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(shoppingList: List<ShoppingListItem>) {
        Log.d("ShoppingListItemAdapter", "Set Data: $shoppingList")
        this.shoppingList = shoppingList
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}
package com.nmn.foodmanager.shoplist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nmn.foodmanager.R
import com.nmn.foodmanager.main.entity.ShoppingListItem

class ShoppingListItemAdapter : ListAdapter<ShoppingListItem, ShoppingListItemAdapter.ShoppingListItemViewHolder>(ShoppingListItemComparator) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListItemViewHolder {
        return ShoppingListItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ShoppingListItemViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.name)
    }

    class ShoppingListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val shoppingListItemItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            shoppingListItemItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): ShoppingListItemViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_shopping_list_item, parent, false)
                return ShoppingListItemViewHolder(view)
            }
        }
    }

    class ShoppingListItemComparator : DiffUtil.ItemCallback<ShoppingListItem>() {
        override fun areItemsTheSame(oldItem: ShoppingListItem, newItem: ShoppingListItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ShoppingListItem, newItem: ShoppingListItem): Boolean {
            return oldItem.quantity == newItem.quantity
        }
    }
}
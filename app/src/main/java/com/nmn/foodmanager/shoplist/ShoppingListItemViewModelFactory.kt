package com.nmn.foodmanager.shoplist

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ShoppingListItemViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShoppingListItemViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ShoppingListItemViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

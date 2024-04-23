package com.nmn.foodmanager.stock

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class StockItemViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StockItemViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StockItemViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

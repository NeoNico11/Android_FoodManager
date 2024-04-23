package com.nmn.foodmanager.stock

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.nmn.foodmanager.main.FoodmanagerDatabase
import com.nmn.foodmanager.main.entity.StockItem
import com.nmn.foodmanager.main.offlineRepository.OfflineStockItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StockItemViewModel(application: Application)  : ViewModel() {


    val allStockItems: LiveData<List<StockItem>>
    private val repository : OfflineStockItemRepository

    init {
        val stockItemDao = FoodmanagerDatabase.getDatabase(application).stockItemDao()
        repository = OfflineStockItemRepository(stockItemDao)
        allStockItems = repository.getAllStockItemsByStockStream(1).asLiveData()
    }

    fun addStockItem(stockItem: StockItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertStockItem(stockItem)
        }
    }

    fun updateStockItem(stockItem: StockItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateStockItem(stockItem)
        }
    }

    fun removeStockItem(stockItem: StockItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteStockItem(stockItem)
        }
    }
}
package com.nmn.foodmanager.shoplist

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.nmn.foodmanager.main.FoodmanagerDatabase
import com.nmn.foodmanager.main.entity.ShoppingListItem
import com.nmn.foodmanager.main.offlineRepository.OfflineShoppingListItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingListItemViewModel(application: Application)  : ViewModel() {

    val allListItems: LiveData<List<ShoppingListItem>>
    private val repository : OfflineShoppingListItemRepository

    init {
        val shoppingListItemDao = FoodmanagerDatabase.getDatabase(application).shoppingListItemDao()
        repository = OfflineShoppingListItemRepository(shoppingListItemDao)
        allListItems = repository.getAllShoppingListItemsByListStream(1).asLiveData()
    }

    fun addShoppingListItem(shoppingListItem: ShoppingListItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertShoppingListItem(shoppingListItem)
        }
    }

    fun updateShoppingListItem(shoppingListItem: ShoppingListItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateShoppingListItem(shoppingListItem)
        }
    }

    fun removeShoppingListItem(shoppingListItem: ShoppingListItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteShoppingListItem(shoppingListItem)
        }
    }
}
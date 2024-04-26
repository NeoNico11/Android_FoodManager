package com.nmn.foodmanager.shoplist

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.nmn.foodmanager.main.FoodmanagerDatabase
import com.nmn.foodmanager.main.entity.ShoppingList
import com.nmn.foodmanager.main.entity.ShoppingListItem
import com.nmn.foodmanager.main.offlineRepository.OfflineShoppingListItemRepository
import com.nmn.foodmanager.main.offlineRepository.OfflineShoppingListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingListItemViewModel(application: Application)  : ViewModel() {

    val allListItems: LiveData<List<ShoppingListItem>>
    private val repository : OfflineShoppingListItemRepository
    private val repositoryShoppingList : OfflineShoppingListRepository

    init {
        val shoppingListItemDao = FoodmanagerDatabase.getDatabase(application).shoppingListItemDao()
        val shoppingListDao = FoodmanagerDatabase.getDatabase(application).shoppingListDao()
        repository = OfflineShoppingListItemRepository(shoppingListItemDao)
        repositoryShoppingList = OfflineShoppingListRepository(shoppingListDao)
        createListShopping(1)
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

    private fun createListShopping(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryShoppingList.insertShoppingList(ShoppingList(id, "stocklist"))
        }
    }
}
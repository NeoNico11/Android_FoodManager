package com.nmn.foodmanager.main.offlineRepository

import com.nmn.foodmanager.main.dao.ShoppingListDao
import com.nmn.foodmanager.main.entity.ShoppingList
import com.nmn.foodmanager.main.repository.ShoppingListRepository
import kotlinx.coroutines.flow.Flow

class OfflineShoppingListRepository(private val shoppingListDao: ShoppingListDao): ShoppingListRepository {
    override fun getAllShoppingListsStream(): Flow<List<ShoppingList>> {
        return shoppingListDao.getAllShoppingLists()
    }

    override fun getShoppingListStream(id: Int): Flow<ShoppingList?> {
        return shoppingListDao.getShoppingList(id)
    }

    override suspend fun insertShoppingList(shoppingList: ShoppingList) {
        shoppingListDao.insert(shoppingList)
    }

    override suspend fun deleteShoppingList(shoppingList: ShoppingList) {
        shoppingListDao.delete(shoppingList)
    }

    override suspend fun updateShoppingList(shoppingList: ShoppingList) {
        shoppingListDao.update(shoppingList)
    }

}
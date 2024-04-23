package com.nmn.foodmanager.main.offlineRepository

import com.nmn.foodmanager.main.dao.ShoppingListItemDao
import com.nmn.foodmanager.main.entity.ShoppingListItem
import com.nmn.foodmanager.main.repository.ShoppingListItemRepository
import kotlinx.coroutines.flow.Flow

class OfflineShoppingListItemRepository(private val shoppingListItemDao: ShoppingListItemDao): ShoppingListItemRepository {

    override fun getAllShoppingListItemsStream(): Flow<List<ShoppingListItem>> {
        return shoppingListItemDao.getAllShoppingListItems()
    }

    override fun getAllShoppingListItemsByListStream(idList: Int): Flow<List<ShoppingListItem>> {
        return shoppingListItemDao.getAllShoppingListItemsByList(idList)
    }

    override fun getShoppingListItemStream(id: Int): Flow<ShoppingListItem?> {
        return shoppingListItemDao.getShoppingListItem(id)
    }

    override suspend fun insertShoppingListItem(shoppingListItem: ShoppingListItem) {
        shoppingListItemDao.insert(shoppingListItem)
    }

    override suspend fun deleteShoppingListItem(shoppingListItem: ShoppingListItem) {
        shoppingListItemDao.delete(shoppingListItem)
    }

    override suspend fun updateShoppingListItem(shoppingListItem: ShoppingListItem) {
        shoppingListItemDao.update(shoppingListItem)
    }

}
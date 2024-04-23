package com.nmn.foodmanager.main.repository

import com.nmn.foodmanager.main.entity.ShoppingListItem
import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [ShoppingListItem] from a given data source.
 */
interface ShoppingListItemRepository {
    /**
     * Retrieve all the shoppingLists from the given data source.
     */
    fun getAllShoppingListItemsStream(): Flow<List<ShoppingListItem>>

    /**
     * Retrieve all the shoppingLists from the given data source by list id.
     */
    fun getAllShoppingListItemsByListStream(idList: Int): Flow<List<ShoppingListItem>>

    /**
     * Retrieve a shoppingList from the given data source that matches with the [id].
     */
    fun getShoppingListItemStream(id: Int): Flow<ShoppingListItem?>

    /**
     * Insert shoppingList in the data source
     */
    suspend fun insertShoppingListItem(shoppingListItem: ShoppingListItem)

    /**
     * Delete shoppingList from the data source
     */
    suspend fun deleteShoppingListItem(shoppingListItem: ShoppingListItem)

    /**
     * Update shoppingList in the data source
     */
    suspend fun updateShoppingListItem(shoppingListItem: ShoppingListItem)
}
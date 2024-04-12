package com.nmn.foodmanager.main.repository

import com.nmn.foodmanager.main.entity.ShoppingList
import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [ShoppingList] from a given data source.
 */
interface ShoppingListRepository {
    /**
     * Retrieve all the shoppingLists from the given data source.
     */
    fun getAllShoppingListsStream(): Flow<List<ShoppingList>>

    /**
     * Retrieve a shoppingList from the given data source that matches with the [id].
     */
    fun getShoppingListStream(id: Int): Flow<ShoppingList?>

    /**
     * Insert shoppingList in the data source
     */
    suspend fun insertShoppingList(shoppingList: ShoppingList)

    /**
     * Delete shoppingList from the data source
     */
    suspend fun deleteShoppingList(shoppingList: ShoppingList)

    /**
     * Update shoppingList in the data source
     */
    suspend fun updateShoppingList(shoppingList: ShoppingList)
}
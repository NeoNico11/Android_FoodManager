package com.nmn.foodmanager.main.repository

import com.nmn.foodmanager.main.entity.StockItem
import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [StockItem] from a given data source.
 */
interface StockItemRepository {
    /**
     * Retrieve all the stockItems from the given data source.
     */
    fun getAllStockItemsStream(): Flow<List<StockItem>>

    /**
     * Retrieve a stockItem from the given data source that matches with the [id].
     */
    fun getStockItemStream(id: Int): Flow<StockItem?>

    /**
     * Insert stockItem in the data source
     */
    suspend fun insertStockItem(stockItem: StockItem)

    /**
     * Delete stockItem from the data source
     */
    suspend fun deleteStockItem(stockItem: StockItem)

    /**
     * Update stockItem in the data source
     */
    suspend fun updateStockItem(stockItem: StockItem)
}
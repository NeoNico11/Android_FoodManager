package com.nmn.foodmanager.main.repository

import com.nmn.foodmanager.main.entity.StockList
import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [StockList] from a given data source.
 */
interface StockListRepository {
    /**
     * Retrieve all the stockLists from the given data source.
     */
    fun getAllStockListsStream(): Flow<List<StockList>>

    /**
     * Retrieve a stockList from the given data source that matches with the [id].
     */
    fun getStockListStream(id: Int): Flow<StockList?>

    /**
     * Insert stockList in the data source
     */
    suspend fun insertStockList(stockList: StockList)

    /**
     * Delete stockList from the data source
     */
    suspend fun deleteStockList(stockList: StockList)

    /**
     * Update stockList in the data source
     */
    suspend fun updateStockList(stockList: StockList)
}
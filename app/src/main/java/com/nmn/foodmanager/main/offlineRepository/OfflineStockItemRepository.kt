package com.nmn.foodmanager.main.offlineRepository

import com.nmn.foodmanager.main.dao.StockItemDao
import com.nmn.foodmanager.main.entity.StockItem
import com.nmn.foodmanager.main.repository.StockItemRepository
import kotlinx.coroutines.flow.Flow

class OfflineStockItemRepository(private val stockItemDao: StockItemDao): StockItemRepository {
    override fun getAllStockItemsStream(): Flow<List<StockItem>> {
        return stockItemDao.getAllStockItems()
    }

    override fun getAllStockItemsByStockStream(idStock: Int): Flow<List<StockItem>> {
        return stockItemDao.getAllStockItemsByStock(idStock)
    }

    override fun getStockItemStream(id: Int): Flow<StockItem?> {
        return stockItemDao.getStockItem(id)
    }

    override suspend fun insertStockItem(stockItem: StockItem) {
        stockItemDao.insert(stockItem)
    }

    override suspend fun deleteStockItem(stockItem: StockItem) {
        stockItemDao.delete(stockItem)
    }

    override suspend fun updateStockItem(stockItem: StockItem) {
        stockItemDao.update(stockItem)
    }

}
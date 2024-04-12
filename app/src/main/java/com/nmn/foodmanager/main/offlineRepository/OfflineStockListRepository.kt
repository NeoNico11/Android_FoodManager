package com.nmn.foodmanager.main.offlineRepository

import com.nmn.foodmanager.main.dao.StockListDao
import com.nmn.foodmanager.main.entity.StockList
import com.nmn.foodmanager.main.repository.StockListRepository
import kotlinx.coroutines.flow.Flow

class OfflineStockListRepository(private val stockListDao: StockListDao): StockListRepository {
    override fun getAllStockListsStream(): Flow<List<StockList>> {
        return stockListDao.getAllStockLists()
    }

    override fun getStockListStream(id: Int): Flow<StockList?> {
        return stockListDao.getStockList(id)
    }

    override suspend fun insertStockList(stockList: StockList) {
        stockListDao.insert(stockList)
    }

    override suspend fun deleteStockList(stockList: StockList) {
        stockListDao.delete(stockList)
    }

    override suspend fun updateStockList(stockList: StockList) {
        stockListDao.update(stockList)
    }

}
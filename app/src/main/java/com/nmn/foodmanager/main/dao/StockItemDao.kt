package com.nmn.foodmanager.main.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nmn.foodmanager.main.entity.StockItem
import kotlinx.coroutines.flow.Flow

@Dao
interface StockItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(stockItem: StockItem)

    @Update
    suspend fun update(stockItem: StockItem)

    @Delete
    suspend fun delete(stockItem: StockItem)

    @Query("SELECT * from stockItems WHERE id = :id")
    fun getStockItem(id: Int): Flow<StockItem>

    @Query("SELECT * from stockItems ORDER BY id_stock ASC")
    fun getAllStockItems(): Flow<List<StockItem>>
}
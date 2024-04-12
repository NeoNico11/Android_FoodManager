package com.nmn.foodmanager.main.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nmn.foodmanager.main.entity.StockList
import kotlinx.coroutines.flow.Flow

@Dao
interface StockListDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(stockList: StockList)

    @Update
    suspend fun update(stockList: StockList)

    @Delete
    suspend fun delete(stockList: StockList)

    @Query("SELECT * from stockLists WHERE id = :id")
    fun getStockList(id: Int): Flow<StockList>

    @Query("SELECT * from stockLists ORDER BY name ASC")
    fun getAllStockLists(): Flow<List<StockList>>
}
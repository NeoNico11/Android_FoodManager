package com.nmn.foodmanager.main.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nmn.foodmanager.main.entity.ShoppingList
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(shoppingList: ShoppingList)

    @Update
    suspend fun update(shoppingList: ShoppingList)

    @Delete
    suspend fun delete(shoppingList: ShoppingList)

    @Query("SELECT * from shoppingLists WHERE id = :id")
    fun getShoppingList(id: Int): Flow<ShoppingList>

    @Query("SELECT * from shoppingLists ORDER BY name ASC")
    fun getAllShoppingLists(): Flow<List<ShoppingList>>
}
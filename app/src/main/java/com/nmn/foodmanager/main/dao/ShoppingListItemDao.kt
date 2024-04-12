package com.nmn.foodmanager.main.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nmn.foodmanager.main.entity.ShoppingListItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(shoppingListItem: ShoppingListItem)

    @Update
    suspend fun update(shoppingListItem: ShoppingListItem)

    @Delete
    suspend fun delete(shoppingListItem: ShoppingListItem)

    @Query("SELECT * from shoppingListItems WHERE id = :id")
    fun getShoppingListItem(id: Int): Flow<ShoppingListItem>

    @Query("SELECT * from shoppingListItems ORDER BY id_list ASC")
    fun getAllShoppingListItems(): Flow<List<ShoppingListItem>>
}
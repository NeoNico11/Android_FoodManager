package com.nmn.foodmanager.main

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nmn.foodmanager.main.dao.ShoppingListDao
import com.nmn.foodmanager.main.dao.ShoppingListItemDao
import com.nmn.foodmanager.main.dao.StockItemDao
import com.nmn.foodmanager.main.dao.StockListDao
import com.nmn.foodmanager.main.entity.ShoppingList
import com.nmn.foodmanager.main.entity.ShoppingListItem
import com.nmn.foodmanager.main.entity.StockItem
import com.nmn.foodmanager.main.entity.StockList

@Database(entities = [
    ShoppingList::class,
    ShoppingListItem::class,
    StockList:: class,
    StockItem:: class,
                     ], version = 2, exportSchema = false)
abstract class FoodmanagerDatabase: RoomDatabase() {

    abstract fun shoppingListDao(): ShoppingListDao

    abstract fun shoppingListItemDao(): ShoppingListItemDao

    abstract fun stockItemDao(): StockItemDao

    abstract fun stockListDao(): StockListDao

    companion object {
        @Volatile
        private var Instance: FoodmanagerDatabase? = null

        fun getDatabase(context: Context): FoodmanagerDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, FoodmanagerDatabase::class.java, "foodmanager_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
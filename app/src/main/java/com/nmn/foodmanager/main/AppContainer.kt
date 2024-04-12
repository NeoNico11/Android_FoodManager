package com.nmn.foodmanager.main

import android.content.Context
import com.nmn.foodmanager.main.offlineRepository.OfflineShoppingListItemRepository
import com.nmn.foodmanager.main.offlineRepository.OfflineShoppingListRepository
import com.nmn.foodmanager.main.offlineRepository.OfflineStockItemRepository
import com.nmn.foodmanager.main.offlineRepository.OfflineStockListRepository
import com.nmn.foodmanager.main.repository.ShoppingListItemRepository
import com.nmn.foodmanager.main.repository.ShoppingListRepository
import com.nmn.foodmanager.main.repository.StockItemRepository
import com.nmn.foodmanager.main.repository.StockListRepository

interface AppContainer {
    val shoppingListRepository: ShoppingListRepository

    val shoppingListItemRepository: ShoppingListItemRepository

    val stockListRepository : StockListRepository

    val stockItemRepository: StockItemRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineShoppingListRepository]
 * and/or [OfflineShoppingListItemRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [ShoppingListRepository]
     */
    override val shoppingListRepository: ShoppingListRepository by lazy {
        OfflineShoppingListRepository(FoodmanagerDatabase.getDatabase(context).shoppingListDao())
    }

    /**
     * Implementation for [ShoppingListItemRepository]
     */
    override val shoppingListItemRepository: ShoppingListItemRepository by lazy {
        OfflineShoppingListItemRepository(FoodmanagerDatabase.getDatabase(context).shoppingListItemDao())
    }

    /**
     * Implementation for [StockListRepository]
     */
    override val stockListRepository: StockListRepository by lazy {
        OfflineStockListRepository(FoodmanagerDatabase.getDatabase(context).stockListDao())
    }

    /**
     * Implementation for [ShoppingListItemRepository]
     */
    override val stockItemRepository: StockItemRepository by lazy {
        OfflineStockItemRepository(FoodmanagerDatabase.getDatabase(context).stockItemDao())
    }
}
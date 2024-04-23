package com.nmn.foodmanager

import android.app.Application
import com.nmn.foodmanager.main.AppContainer
import com.nmn.foodmanager.main.AppDataContainer
import com.nmn.foodmanager.main.FoodmanagerDatabase
import com.nmn.foodmanager.main.offlineRepository.OfflineShoppingListItemRepository

class FoodManagerApplication: Application() {
    val database by lazy { FoodmanagerDatabase.getDatabase(this) }
    val repository by lazy { OfflineShoppingListItemRepository(database.shoppingListItemDao()) }

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
package com.nmn.foodmanager.main.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shoppingLists")
data class ShoppingList(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String
)

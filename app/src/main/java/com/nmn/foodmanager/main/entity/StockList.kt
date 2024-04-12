package com.nmn.foodmanager.main.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stockLists")
data class StockList(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String
)

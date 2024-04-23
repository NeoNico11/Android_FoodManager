package com.nmn.foodmanager.main.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "stockItems",
    foreignKeys = [
    ForeignKey(entity = StockList::class,
        parentColumns = ["id"],
        childColumns = ["id_stock"],
        onDelete = ForeignKey.CASCADE
    )
])
data class StockItem(
    @PrimaryKey (autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "id_stock") val idStock: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "quantity") val quantity: Int,
    @ColumnInfo(name = "expiration_date") val expirationDate: Long
)

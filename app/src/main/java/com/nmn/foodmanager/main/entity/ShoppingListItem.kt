package com.nmn.foodmanager.main.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "shoppingListItems",
    foreignKeys = [
    ForeignKey(entity = ShoppingList::class,
        parentColumns = ["id"],
        childColumns = ["id_list"],
        onDelete = ForeignKey.CASCADE
    )
])
data class ShoppingListItem(
    @PrimaryKey (autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "id_list") val idList: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "quantity") var quantity: Int,
    @ColumnInfo(name = "status") var status: Boolean
)

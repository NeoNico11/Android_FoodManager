package com.nmn.foodmanager.shoplist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.nmn.foodmanager.main.entity.ShoppingListItem
import com.nmn.foodmanager.main.offlineRepository.OfflineShoppingListItemRepository

class ShoppingListItemViewModel(private val repository: OfflineShoppingListItemRepository) : ViewModel() {

    val allListItems: LiveData<List<ShoppingListItem>> = repository.getAllShoppingListItemsStream().asLiveData()

    data class ShoppingListItemUiState(
        val shoppingListItemDetails: ShoppingListItemDetails = ShoppingListItemDetails(),
        val isEntryValid: Boolean = false
    )

    data class ShoppingListItemDetails(
        val id: Int = 0,
        val idList: Long = 0,
        val name: String = "",
        val quantity: String = "",
    )

    /**
     * Extension function to convert [ShoppingListItemDetails] to [ShoppingListItem].
     * [ShoppingListItemDetails.quantity] is not a valid [Int], then the quantity will be set to 0
     */
    fun ShoppingListItemDetails.toItem(): ShoppingListItem = ShoppingListItem(
        id = id,
        idList = idList,
        name = name,
        quantity = quantity.toIntOrNull() ?: 0
    )

    /**
     * Extension function to convert [ShoppingListItem] to [ShoppingListItemUiState]
     */
    fun ShoppingListItem.toItemUiState(isEntryValid: Boolean = false): ShoppingListItemUiState = ShoppingListItemUiState(
        shoppingListItemDetails = this.toShoppingListItemDetails(),
        isEntryValid = isEntryValid
    )

    /**
     * Extension function to convert [ShoppingListItem] to [ShoppingListItemDetails]
     */
    fun ShoppingListItem.toShoppingListItemDetails(): ShoppingListItemDetails = ShoppingListItemDetails(
        id = id,
        idList = idList,
        name = name,
        quantity = quantity.toString()
    )
}
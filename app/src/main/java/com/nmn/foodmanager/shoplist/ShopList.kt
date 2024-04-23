package com.nmn.foodmanager.shoplist

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nmn.foodmanager.databinding.FragmentShopListBinding
import com.nmn.foodmanager.main.entity.ShoppingListItem

class ShopList : Fragment() {

    private var _binding : FragmentShopListBinding?= null
    private val binding get() = _binding!!

    private lateinit var mShoppingListItemViewModel: ShoppingListItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("ShopList", "Create Fragment ShopList")
        _binding = FragmentShopListBinding.inflate(inflater, container, false)


        // Recyclerview - LayoutManager
        val layoutManager = LinearLayoutManager(requireContext())
        Log.d("ShopList", "Binding layoutManager to recyclerviewShopList : $layoutManager")
        binding.recyclerviewShopList.layoutManager = layoutManager
        // Recyclerview - Adapter
        val adapter = ShoppingListItemAdapter(::deleteItemFromDatabase, ::updateItemIntoDatabase)
        Log.d("ShopList", "Binding adapter to recyclerviewShopList : $adapter")
        binding.recyclerviewShopList.adapter = adapter

        // UserViewModel
        mShoppingListItemViewModel = ViewModelProvider(this, ShoppingListItemViewModelFactory(requireActivity().application))[ShoppingListItemViewModel::class.java]
        mShoppingListItemViewModel.allListItems.observe(viewLifecycleOwner) { shoppingListItemList ->
            adapter.setData(shoppingListItemList)
        }

        binding.addToShopList.setOnClickListener {
            insertItemToDatabase()
        }

        return binding.root
    }

    private fun insertItemToDatabase() {
        val shopListItem = binding.itemToAdd.text.toString()


        if (inputCheck(shopListItem)) {
            val item = ShoppingListItem(0, 1, shopListItem, 1, false)
            mShoppingListItemViewModel.addShoppingListItem(item)
            Toast.makeText(requireContext(), "Elément ajouté avec succés", Toast.LENGTH_LONG).show()
            binding.itemToAdd.text.clear()
        } else {
            Toast.makeText(requireContext(), "Veuillez reinseigner un article à ajouter à votre liste", Toast.LENGTH_LONG).show()
        }
    }

    private fun updateItemIntoDatabase(shoppingListItem: ShoppingListItem) {
        mShoppingListItemViewModel.updateShoppingListItem(shoppingListItem)
    }

    private fun deleteItemFromDatabase(shoppingListItem: ShoppingListItem) {
        mShoppingListItemViewModel.removeShoppingListItem(shoppingListItem)
    }

    private fun inputCheck(shopListItem: String): Boolean {
        return !(TextUtils.isEmpty(shopListItem))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
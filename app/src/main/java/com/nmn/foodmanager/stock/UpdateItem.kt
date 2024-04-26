package com.nmn.foodmanager.stock

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.nmn.foodmanager.databinding.FragmentUpdateItemBinding
import com.nmn.foodmanager.main.entity.StockItem

class UpdateItem(stockItemInit: StockItem) : DialogFragment() {

    private var _binding : FragmentUpdateItemBinding?= null
    private val binding get() = _binding!!

    private val stockItem = stockItemInit

    private lateinit var mStockItemViewModel: StockItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateItemBinding.inflate(inflater, container, false)

        val btnAdd = binding.buttonUpdateItem
        btnAdd.setOnClickListener {
            updateQty()
            dismiss()
        }

        // UserViewModel
        mStockItemViewModel = ViewModelProvider(this, StockItemViewModelFactory(requireActivity().application))[StockItemViewModel::class.java]

        return binding.root
    }

    private fun updateQty() {
        val qty = binding.editTextQtyUpdateItem.text
        if (inputCheck(qty.toString())) {
            stockItem.quantity = qty.toString().toInt()
            mStockItemViewModel.updateStockItem(stockItem)
            Toast.makeText(requireContext(), "Elément modifié avec succés", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(requireContext(), "Veuillez reinseigner tous les champs", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(shopListItem: String): Boolean {
        return !(TextUtils.isEmpty(shopListItem))
    }
}
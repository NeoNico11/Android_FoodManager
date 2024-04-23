package com.nmn.foodmanager.stock

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.nmn.foodmanager.databinding.FragmentAddItemToStockBinding
import com.nmn.foodmanager.main.entity.StockItem

class AddItemToStock : DialogFragment() {

    private var _binding : FragmentAddItemToStockBinding?= null
    private val binding get() = _binding!!

    private lateinit var mStockItemViewModel: StockItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddItemToStockBinding.inflate(inflater, container, false)

        // UserViewModel
        mStockItemViewModel = ViewModelProvider(this, StockItemViewModelFactory(requireActivity().application))[StockItemViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnAdd = binding.buttonAddItem

        btnAdd.setOnClickListener {
            insertItemToDatabase()
            dismiss()
        }
    }

    private fun insertItemToDatabase() {
        val stockItemName = binding.editTextTextAddItem.text.toString()
        val stockItemQuantity = binding.editTextNumberAddItem.text.toString()
        val stockItemDate = binding.editTextDateAddItem.text.toString()


        if (inputCheck(stockItemName) && inputCheck(stockItemQuantity) && inputCheck(stockItemDate)) {
            val item = StockItem(0, 1, stockItemName, stockItemQuantity.toInt(), stockItemDate.toLong())
            mStockItemViewModel.addStockItem(item)
            Toast.makeText(requireContext(), "Elément ajouté avec succés", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(requireContext(), "Veuillez reinseigner tous les champs", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(shopListItem: String): Boolean {
        return !(TextUtils.isEmpty(shopListItem))
    }
}
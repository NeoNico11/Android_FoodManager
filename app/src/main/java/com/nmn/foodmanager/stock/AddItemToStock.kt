package com.nmn.foodmanager.stock

import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.nmn.foodmanager.databinding.FragmentAddItemToStockBinding
import com.nmn.foodmanager.main.entity.StockItem
import java.time.LocalDate

class AddItemToStock : DialogFragment() {

    private var _binding : FragmentAddItemToStockBinding?= null
    private val binding get() = _binding!!

    private lateinit var mStockItemViewModel: StockItemViewModel

    private var year: Int? = null
    private var month: Int? = null
    private var day: Int? = null

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

        val datePicker = binding.pickDate
        datePicker.setOnClickListener {
            val newFragment = AddItemDatePickerFragment(::setDate)
            newFragment.show((activity as AppCompatActivity).supportFragmentManager, "showPopUp")
        }
    }
    private fun insertItemToDatabase() {
        val stockItemName = binding.editTextTextAddItem.text.toString()
        val stockItemQuantity = binding.editTextNumberAddItem.text.toString()

        val timestamp: LocalDate? = if(year != null && month != null && day != null) {
            LocalDate.of(year!!, month!!, day!!)
        } else {
            null
        }
        val stockItemDate = timestamp?.toEpochDay()

        if (inputCheck(stockItemName) && inputCheck(stockItemQuantity) && stockItemDate != null) {
            val item = StockItem(0, 1, stockItemName, stockItemQuantity.toInt(), stockItemDate)
            mStockItemViewModel.addStockItem(item)
            Toast.makeText(requireContext(), "Elément ajouté avec succés", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(requireContext(), "Veuillez reinseigner tous les champs", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(shopListItem: String): Boolean {
        return !(TextUtils.isEmpty(shopListItem))
    }



    @RequiresApi(Build.VERSION_CODES.O)
    fun setDate(year: Int, month: Int, day: Int) {
        this.year = year
        this.month = month
        this.day = day

        updateTextView(LocalDate.of(year, month, day))
        Log.d("ItemDatePicker", "TEST: $year")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateTextView(date: LocalDate) {
        Log.d("ItemDatePicker", "Update TextView text with : $date")
        binding.textViewAddItemDateExpiration.text = date.toString()
    }
}
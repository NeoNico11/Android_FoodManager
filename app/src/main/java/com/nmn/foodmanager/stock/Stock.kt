package com.nmn.foodmanager.stock

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nmn.foodmanager.databinding.FragmentStockBinding
import com.nmn.foodmanager.main.entity.StockItem

/**
 * A simple [Fragment] subclass.
 * Use the [Stock.newInstance] factory method to
 * create an instance of this fragment.
 */
class Stock : Fragment() {

    private var _binding : FragmentStockBinding?= null
    private val binding get() = _binding!!

    private lateinit var mStockItemViewModel: StockItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("Stock", "Create Fragment Stock")
        _binding = FragmentStockBinding.inflate(inflater, container, false)

        // Recyclerview - LayoutManager
        val layoutManager = LinearLayoutManager(requireContext())
        Log.d("Stock", "Binding layoutManager to recyclerviewShopList : $layoutManager")
        binding.recyclerviewStock.layoutManager = layoutManager
        // Recyclerview - Adapter
        val adapter = StockItemAdapter(::deleteItemFromDatabase)
        Log.d("Stock", "Binding adapter to recyclerviewShopList : $adapter")
        binding.recyclerviewStock.adapter = adapter

        mStockItemViewModel = ViewModelProvider(this, StockItemViewModelFactory(requireActivity().application))[StockItemViewModel::class.java]
        mStockItemViewModel.allStockItems.observe(viewLifecycleOwner) { stockItemList ->
            adapter.setData(stockItemList)
        }

        binding.buttonAddItemToStock.setOnClickListener {
            val showPopUp = AddItemToStock()
            showPopUp.show((activity as AppCompatActivity).supportFragmentManager, "showPopUp")
        }

        return binding.root
    }

    private fun deleteItemFromDatabase(stockItem: StockItem) {
        mStockItemViewModel.removeStockItem(stockItem)
    }
}
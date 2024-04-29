package com.nmn.foodmanager.settings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nmn.foodmanager.databinding.FragmentSettingsBinding

class Settings : Fragment() {

    private var _binding : FragmentSettingsBinding?= null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("Settings", "Create Fragment Settings")
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        
        return binding.root
    }
}
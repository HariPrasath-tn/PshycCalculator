package com.example.pshyccalculator.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.pshyccalculator.R
import com.example.pshyccalculator.databinding.FragmentLandscapeBinding

class Landscape : Fragment() {
    private lateinit var binding:FragmentLandscapeBinding;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_landscape, container, false);
        return binding.root;
    }
}
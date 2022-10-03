package com.example.pshyccalculator.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import com.example.pshyccalculator.R
import com.example.pshyccalculator.databinding.FragmentCalHistoryBinding


class CalHistory : Fragment() {
    private lateinit var binding:FragmentCalHistoryBinding;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cal_history, container, false);
        loadData(this);

        return binding.root;
    }

    private fun loadData(calHistory: CalHistory){
        
    }
}
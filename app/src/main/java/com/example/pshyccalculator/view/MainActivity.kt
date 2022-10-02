package com.example.pshyccalculator.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.pshyccalculator.R
import com.example.pshyccalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // creating the data binding container inorder initiate data binding
    private lateinit var binding:ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // initializing the data binding for the activity main
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }
}
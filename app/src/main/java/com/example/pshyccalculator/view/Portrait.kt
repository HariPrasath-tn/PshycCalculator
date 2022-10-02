package com.example.pshyccalculator.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.pshyccalculator.R
import com.example.pshyccalculator.databinding.FragmentPortraitBinding
import com.example.pshyccalculator.model.History
import com.example.pshyccalculator.model.MainModelRepository
import com.example.pshyccalculator.view_model.MainViewModel

class Portrait : Fragment() {
    private lateinit var binding:FragmentPortraitBinding;
    private lateinit var viewModel: MainViewModel;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_portrait, container, false);

        // initializing the view Model
        viewModel = ViewModelProvider(this)[MainViewModel::class.java];
        viewModel.setViewModel(viewModel);
        binding.data = viewModel;
        binding.lifecycleOwner = this;


        binding.apply {

            // button to redirect fragment to history fragment
            hist.setOnClickListener {
                view?.findNavController()?.navigate(R.id.action_portrait_to_calHistory);
            }
            // number buttons
            one.setOnClickListener { viewModel.operate("1") }
            two.setOnClickListener { viewModel.operate("2") }
            three.setOnClickListener { viewModel.operate("3") }
            four.setOnClickListener { viewModel.operate("4") }
            five.setOnClickListener { viewModel.operate("5") }
            six.setOnClickListener { viewModel.operate("6") }
            seven.setOnClickListener { viewModel.operate("7") }
            eight.setOnClickListener { viewModel.operate("8") }
            nine.setOnClickListener { viewModel.operate("9") }
            zero.setOnClickListener { viewModel.operate("0") }

            // special symbol buttons
            openParenthesis.setOnClickListener { viewModel.operate("(") }
            closeParenthesis.setOnClickListener { viewModel.operate(")") }
            add.setOnClickListener { viewModel.operate("+") }
            subtract.setOnClickListener { viewModel.operate("-") }
            multiply.setOnClickListener { viewModel.operate("*") }
            divide.setOnClickListener { viewModel.operate("/") }
            remainder.setOnClickListener { viewModel.operate("%") }
            dot.setOnClickListener { viewModel.operate(".") }

            // operation buttons
            delete.setOnClickListener { viewModel.operate("del") }
            clear.setOnClickListener { viewModel.operate("clear") }

            // initiating expression solving
            equal.setOnClickListener {
                if(!viewModel.solveThis()){
                    binding.invalidtext.visibility = View.VISIBLE;

                    Thread(Runnable {
                            Thread.sleep(3000);
                        binding.invalidtext.visibility = View.INVISIBLE;
                    }).start();
                }

            }

        }
        return binding.root;
    }

}
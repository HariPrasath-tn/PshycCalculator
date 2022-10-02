package com.example.pshyccalculator.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pshyccalculator.model.History
import com.example.pshyccalculator.model.MainModelRepository

class MainViewModel : ViewModel() {

    // creating mutable live data for result and expression
    private val _result = MutableLiveData<String>();
    private val _expression = MutableLiveData<String>();
    init{
        setValue();
    }

    companion object{
        @JvmStatic lateinit var mainViewModel:MainViewModel;
    }


    /*
     * getter and setters for above variables
     */
    val result:LiveData<String>
        get() {return _result}
    val expression:LiveData<String>
        get() {return _expression}
    fun setViewModel(viewModel: MainViewModel){
        mainViewModel = viewModel;
    }
    fun getViewModel():MainViewModel{
        return mainViewModel;
    }
    fun setValue(){
        _result.value = History.getInstance().result;
        _expression.value = History.getInstance().expression;
    }



    // methods to communicate with model
    fun operate(opType:String){
        MainModelRepository.textOperations(opType);
    }
    fun solveThis():Boolean{
        return MainModelRepository().solveThis(History.getInstance().expression);
    }
}
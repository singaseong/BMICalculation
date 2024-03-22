package com.example.bmi_calculator

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class BMIAppViewModel: ViewModel(){
    private val _repository: BMICalRepository = BMICalRepository()
    private val _bodyStatus = mutableStateOf(_repository.getBMIReturnOutput().bodyStatus)
    private val _BMIValue = mutableStateOf(_repository.getBMIReturnOutput().BMIValue)

    //from user input
    var inputH  by mutableStateOf("")
    var inputW  by mutableStateOf("")

    val BMIResultModel: MutableState<String> = _bodyStatus
    val outputBMI_RoundModel: MutableState<Double> = _BMIValue


    fun getBMICalResult(){
        _repository.BMICalculation(inputH, inputW)
        BMIResultModel.value= _repository.getBMIReturnOutput().bodyStatus
        outputBMI_RoundModel.value = _repository.getBMIReturnOutput().BMIValue
    }
}


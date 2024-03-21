package com.example.bmi_calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class BMIAppViewModel: ViewModel(){

    var inputH  by mutableStateOf("")
    var inputW  by mutableStateOf("")
    var BMIResultModel by mutableStateOf("")
    var outputBMI_RoundModel by mutableStateOf(0.0)

    fun getBMICalResult(){
        BMIResultModel= BMICalculation(inputH, inputW).BMICalResult().bodyStatus
        outputBMI_RoundModel = BMICalculation(inputH, inputW).BMICalResult().BMIValue
    }


}
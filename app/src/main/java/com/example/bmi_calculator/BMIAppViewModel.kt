package com.example.bmi_calculator

import androidx.lifecycle.ViewModel


class BMIAppViewModel: ViewModel(){

    private var _heightValue = 1.0
    private var _weightValue = 1.0

    fun setHeightValue(value: Double){
        _heightValue = value
    }

    fun setWeightValue(value: Double){
        _weightValue = value
    }

    fun getBMICalResult():Double {
        return BMICalculation().BMICalResult(_heightValue,_weightValue)
    }

    fun getBMICheck():String {
        return BMICalculation().BMICheck(getBMICalResult())
    }
}
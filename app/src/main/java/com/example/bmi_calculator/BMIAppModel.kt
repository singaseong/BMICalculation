package com.example.bmi_calculator

import kotlin.math.pow

data class BMIReturnOutput (val bodyStatus :String, val BMIValue:Double){

}
class BMICalculation (private var inputH:String, private var inputW:String) {

    //BMI calculation
    fun BMICalResult():BMIReturnOutput{
        var result:String = ""
        var outputBMI_Round =0.0
        var outputBMIValue = 0.0

        var inputH_Double = inputH.toDoubleOrNull() ?:0.0
        var inputW_Double = inputW.toDoubleOrNull() ?:0.0

        if(inputH_Double == 0.0 || inputW_Double == 0.0){
            result=""
            outputBMI_Round = 0.0
        }else {
            outputBMIValue = (inputW_Double / inputH_Double.pow(2) * 10000)
            outputBMI_Round = String.format("%.2f", outputBMIValue).toDouble()
            result = BMICheck(outputBMI_Round)
        }
        return BMIReturnOutput(result, outputBMI_Round)
        // return outputBMI_Round


    }
    fun BMICheck(_outputBMI_Round:Double):String{
        var _result = if (_outputBMI_Round >0.0 && _outputBMI_Round<18.5 ) {
            "저체중"
        } else if(_outputBMI_Round>=18.5 && _outputBMI_Round<25){
            "정상"

        }else if(_outputBMI_Round>=25 && _outputBMI_Round<30){
            "과체중"

        }else if(_outputBMI_Round>=30){
            "비만"

        }else {
            ""

        }
        return _result
    }
    //BMI 결과에 대한 범주 확인




}




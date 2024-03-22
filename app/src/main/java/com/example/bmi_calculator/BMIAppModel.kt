package com.example.bmi_calculator

import kotlin.math.pow

data class BMIReturnOutput (var bodyStatus :String, var BMIValue:Double){

}
class BMICalRepository {

    private var _BMIReturnOutput= BMIReturnOutput("",0.0)

   //get the result data
    fun getBMIReturnOutput() = _BMIReturnOutput

    //BMI calculation
    fun BMICalculation(_inputH:String, _inputW:String){

        var outputBMI_Round =0.0
        var outputBMIValue = 0.0

        var inputH_Double = _inputH.toDoubleOrNull() ?:0.0
        var inputW_Double = _inputW.toDoubleOrNull() ?:0.0

        if(inputH_Double == 0.0 || inputW_Double == 0.0){
            _BMIReturnOutput.bodyStatus=""
            _BMIReturnOutput.BMIValue = 0.0
        }else {
            outputBMIValue = (inputW_Double / inputH_Double.pow(2) * 10000)
            outputBMI_Round = String.format("%.2f", outputBMIValue).toDouble()
            _BMIReturnOutput.bodyStatus = BMICheck(outputBMI_Round)
        }
        // return outputBMI_Round


    }
    //BMI 결과에 대한 범주 확인
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
}




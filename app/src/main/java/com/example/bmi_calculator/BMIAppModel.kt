package com.example.bmi_calculator

import kotlin.math.pow


class BMICalculation  {

    fun BMICalResult (heightValue:Double, weightValue:Double) : Double {
        var outputBMI = (heightValue/weightValue.pow(2)*10000)
        var outputBMI_Round = String.format("%.2f", outputBMI).toDouble()
        return outputBMI_Round
    }

    fun BMICheck(outputBMI_Round:Double):String{

        var result : String = ""

        result = if (outputBMI_Round < 18.5) {
            "저체중"

        }else if(outputBMI_Round>=18.5 && outputBMI_Round<25){
            "정상"
        }else if(outputBMI_Round>=25 && outputBMI_Round<30){
            "과체중"
        }else {
            "비만"
        }
        return result
    }

}



/*

fun main() {
    var height :Double = 180.0
    var weight :Double = 83.0

    var result_BMI = (weight/height.pow(2)*10000)
    var result_BMI_Round = String.format("%.2f", result_BMI).toDouble()
    println(result_BMI_Round)

    var input1 = 180.0


    var input2 = 50.0


    var dataouptut = BMICalculation(input1, input2).outputBMI_Round
    var dataResult = BMICalculation(input1, input2).BMIcheck()

    println(dataouptut)
    println(dataResult)

}
*/

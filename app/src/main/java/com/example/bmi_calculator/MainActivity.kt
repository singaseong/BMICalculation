package com.example.bmi_calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.bmi_calculator.ui.theme.BMI_CalculatorTheme

import kotlin.math.pow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //val viewModel: BMIAppViewModel = viewModel()

            BMI_CalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyBMIApp()
                }
            }
        }
    }
}





@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
@Preview


fun MyBMIApp( ){

    var inputH by remember { mutableStateOf("") }
    var inputW by remember { mutableStateOf("") }
    var outputBMIValue by remember{mutableStateOf(0.0)}
    var outputBMI_Round by remember{mutableStateOf(0.0)}
    var result by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current // 커서 focus disable, 키보드 hide



    //BMI calculation
    fun BMICalResult()  {

        var inputH_Double = inputH.toDoubleOrNull() ?:0.0
        var inputW_Double = inputW.toDoubleOrNull() ?:0.0

        if(inputH_Double == 0.0 || inputW_Double == 0.0){
            result=""
            outputBMI_Round = 0.0
        }else {
            outputBMIValue = (inputW_Double / inputH_Double.pow(2) * 10000)
            outputBMI_Round = String.format("%.2f", outputBMIValue).toDouble()
        }// return outputBMI_Round
    }

    //BMI 결과에 대한 범주 확인
    fun BMICheck(){
        if (outputBMI_Round >0.0 && outputBMI_Round<18.5 ) {
            result = "저체중"
        } else if(outputBMI_Round>=18.5 && outputBMI_Round<25){
            result = "정상"

        }else if(outputBMI_Round>=25 && outputBMI_Round<30){
            result = "과체중"

        }else if(outputBMI_Round>=30){
            result = "비만"

        }else {
            result = ""

            }
        }



    Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource ()},// user가 클릭하는지를 추적하기 위함. 이게 없으면 아래 indication = null 이 에러가 뜸
                    indication = null, // click 했을때 ripple 효과 없앰
                    onClick = { focusManager.clearFocus() } // 커서 focus disable, 키보드 hide
                ),

            horizontalAlignment = Alignment.CenterHorizontally,


        ){

           //title
            Text(text = "BMI Calculator", style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(16.dp))
           //Input UI from User
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ){
                //input Height
                Text(
                    text = "Height",
                    modifier = Modifier
                        .padding(8.dp)
                        .wrapContentSize()

                )

                OutlinedTextField(
                    value = inputH,
                    onValueChange ={inputH = it},
                    label = {Text("Enter your Height")},
                    singleLine = true

                )


                Text(
                    text = "cm",
                    modifier = Modifier
                        .padding(8.dp)
                        .wrapContentSize(),
                    style = TextStyle(textAlign = TextAlign.Center)

                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            //Input Weight
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = "Weight",
                    modifier = Modifier
                        .padding(4.dp) // 4dp 설정 이유는 위 Height Row 와 align 맞추기 위함.
                        .wrapContentSize()
                )

                OutlinedTextField(
                    value = inputW,
                    onValueChange ={inputW = it},
                    label = {Text("Enter your weight")},
                    singleLine = true

                )

                Text(
                    text = "kg",
                    modifier = Modifier
                        .padding(8.dp)
                        .wrapContentSize(),
                    style = TextStyle(textAlign = TextAlign.Center)

                    )


            }
            //계산 실행
            Spacer(modifier = Modifier.height(8.dp))


            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {

                Button(onClick = {
                    BMICalResult()
                    BMICheck()
                    focusManager.clearFocus() // 커서 focus disable, 키보드 hide
                })
                {
                    Text(text = "Calculate")
                }

                //reset
                Button(onClick = {
                    inputH =""
                    inputW =""
                    result =""
                    outputBMI_Round = 0.0
                    focusManager.clearFocus() // 커서 focus disable, 키보드 hide
                }
                )
                {
                    Text(text = "RESET")
                }

            }
            //계산 결과값 산출
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "   BMI Result  : ${outputBMI_Round} ${result}",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(),
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(8.dp))


        // 저체중 인 경우 image 1
        // 정상인 경우 image 2
        // 과체중인 경우 image 3
        // 비만인 경우 image 4
        when (result) {
            "저체중" -> {
                Image(
                    painter = painterResource(id =R.drawable.image1),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(300.dp)
                        .clip(CircleShape)
                )
            }
            "정상" -> {
                Image(
                    painter = painterResource(id =R.drawable.image2),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(300.dp)
                        .clip(CircleShape)
                )

            }
            "과체중" -> {
                Image(
                    painter = painterResource(id =R.drawable.image3),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(300.dp)
                        .clip(CircleShape)
                )

            }
            "비만" -> {
                Image(
                    painter = painterResource(id =R.drawable.image4),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(300.dp)
                        .clip(CircleShape)
                )

            }
            else -> {
                //아무것도 아닌 경우는 image 없음
            }
        }

        }

}



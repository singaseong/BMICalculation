package com.example.bmi_calculator

import android.annotation.SuppressLint
import android.widget.ScrollView
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.math.pow

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun MyBMIApp(viewModel: BMIAppViewModel){

    val BMIViewModel: BMIAppViewModel = viewModel()
    val focusManager = LocalFocusManager.current // 커서 focus disable, 키보드 hide


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource () },// user가 클릭하는지를 추적하기 위함. 이게 없으면 아래 indication = null 이 에러가 뜸
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
                value = viewModel.inputH,
                onValueChange ={viewModel.inputH = it},
                label = { Text("Enter your Height") },
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
                value = viewModel.inputW,
                onValueChange ={viewModel.inputW = it},
                label = { Text("Enter your weight") },
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
                BMIViewModel.inputH = viewModel.inputH
                BMIViewModel.inputW = viewModel.inputW
                BMIViewModel.getBMICalResult()
                focusManager.clearFocus() // 커서 focus disable, 키보드 hide
            })
            {
                Text(text = "Calculate")
            }
            //reset
            Button(onClick = {
                viewModel.inputH =""
                viewModel.inputW =""
                BMIViewModel.inputH = viewModel.inputH
                BMIViewModel.inputW = viewModel.inputW
                BMIViewModel.BMIResultModel.value =""
                BMIViewModel.outputBMI_RoundModel.value = 0.0
                focusManager.clearFocus() // 커서 focus disable, 키보드 hide
                }
            ){
                Text(text = "RESET")
            }
        }
        //계산 결과값 산출
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "   BMI Result  : ${BMIViewModel.outputBMI_RoundModel.value} ${BMIViewModel.BMIResultModel.value}",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(),
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

         when (BMIViewModel.BMIResultModel.value) {
            "저체중" -> {
                ResultImage(R.drawable.image1)
            }
            "정상" -> {
                ResultImage(R.drawable.image2)
            }
            "과체중" -> {
                ResultImage(R.drawable.image3)
            }
            "비만" -> {
                ResultImage(R.drawable.image4)
            }
            else -> {
                //아무것도 아닌 경우는 image 없음
            }
        }
    }
}
@Composable
fun ResultImage(id : Int) {
    Image(
        painter = painterResource(id =id),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(300.dp)
            .clip(CircleShape)
    )
}
package com.example.text_diaplayer

import android.R.attr.textSize
import android.annotation.SuppressLint
import android.os.Bundle
import android.service.autofill.OnClickAction
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.text_diaplayer.ui.theme.Text_diaplayerTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var localConfig = LocalConfiguration.current
            var width = localConfig.screenWidthDp
            var height = localConfig.screenHeightDp

            var fieldText by rememberSaveable() { mutableStateOf("") }
            var displayText by rememberSaveable() { mutableStateOf("輸入文字") }
            var textSize by rememberSaveable() { mutableStateOf(40f) }
            var displayStyle by rememberSaveable() { mutableStateOf(false) }
            var offsetX by rememberSaveable() { mutableStateOf(0f) }

            var alignmentNumber by remember { mutableStateOf(0) }
            var moveNumber by rememberSaveable() { mutableStateOf(0) }
            var colorNumber by rememberSaveable() { mutableStateOf(0) }

            var alignIconList = listOf(R.drawable.outline_align_horizontal_left_24,R.drawable.outline_align_horizontal_center_24,R.drawable.outline_align_horizontal_right_24)
            var moveIconList = listOf(R.drawable.outline_play_arrow_24,R.drawable.outline_stop_24)
            var alignList = listOf(TextAlign.Left, TextAlign.Center, TextAlign.Right)
            var colorList = listOf(Color.Gray, Color.Red, Color.Yellow, Color.Blue, Color.Green, Color.White, Color.Black)

            LaunchedEffect(Unit) {
                while (true){
                    delay(10)
                    if (moveNumber == 1){
                        offsetX += 3f
                        if (offsetX > width / 2 + textSize * displayText.length / 2){
                            offsetX = -width / 2f - textSize * displayText.length / 2
                        }
                    }
                }
            }

            Text_diaplayerTheme {
                Scaffold(
                    Modifier.fillMaxSize(),
                    ) {
                    Column(
                        Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(Modifier.height(40.dp))
                        if (!displayStyle){
                            TextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = fieldText,
                                onValueChange = {
                                    fieldText = it
                                },
                            )
                            Spacer(
                                Modifier.height(20.dp)
                            )
                            if (width < height){
                                Row(
                                    Modifier.fillMaxWidth(),
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(50.dp)
                                            .background(
                                                color = Color(0xFFFFFFFF),
                                                shape = CircleShape
                                            )
                                            .clickable(){
                                            displayText = fieldText
                                        },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Image(
                                            painter = painterResource(R.drawable.outline_text_increase_24),
                                            contentDescription = "",
                                            modifier = Modifier.size(36.dp)
                                        )
                                    }
                                    Spacer(Modifier.width(20.dp))
                                    Box(
                                        modifier = Modifier
                                            .size(50.dp)
                                            .background(
                                                color = Color(0xFFFFFFFF),
                                                shape = CircleShape
                                            )
                                            .clickable(){
                                                if (alignmentNumber < 2){
                                                    alignmentNumber += 1
                                                } else {
                                                    alignmentNumber = 0
                                                }
                                            },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Image(
                                            painter = painterResource(alignIconList[alignmentNumber]),
                                            contentDescription = "",
                                            modifier = Modifier.size(36.dp)
                                        )
                                    }
                                    Spacer(Modifier.width(20.dp))
                                    Box(
                                        modifier = Modifier
                                            .size(50.dp)
                                            .background(
                                                color = Color(0xFFFFFFFF),
                                                shape = CircleShape
                                            )
                                            .clickable(){
                                                if (moveNumber == 0){
                                                    moveNumber = 1
                                                    alignmentNumber = 1
                                                }else {
                                                    moveNumber = 0
                                                    offsetX = 0f
                                                }
                                            },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Image(
                                            painter = painterResource(moveIconList[moveNumber]),
                                            contentDescription = "",
                                            modifier = Modifier.size(36.dp)
                                        )
                                    }
                                    Spacer(Modifier.width(20.dp))
                                    Box(
                                        modifier = Modifier
                                            .size(50.dp)
                                            .background(
                                                color = Color(0xFFFFFFFF),
                                                shape = CircleShape
                                            )
                                            .clickable(){
                                                if (colorNumber < 6){
                                                    colorNumber += 1
                                                } else {
                                                    colorNumber = 0
                                                }
                                            },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(24.dp)
                                                .background(
                                                    color = colorList[colorNumber],
                                                    shape = CircleShape
                                                )
                                        )
                                    }
                                }
                                Spacer(
                                    Modifier.height(20.dp)
                                )
                                Slider(
                                    onValueChange = {
                                        textSize = it
                                    },
                                    value = textSize,
                                    valueRange = 20f..200f,
                                )
                                Spacer(
                                    Modifier.height(20.dp)
                                )
                            }else {
                                Row(
                                    Modifier.fillMaxWidth(),
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(50.dp)
                                            .background(
                                                color = Color(0xFFFFFFFF),
                                                shape = CircleShape
                                            )
                                            .clickable(){
                                                displayText = fieldText
                                            },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Image(
                                            painter = painterResource(R.drawable.outline_text_rotation_none_24),
                                            contentDescription = "",
                                            modifier = Modifier.size(36.dp)
                                        )
                                    }
                                    Spacer(Modifier.width(20.dp))
                                    Box(
                                        modifier = Modifier
                                            .size(50.dp)
                                            .background(
                                                color = Color(0xFFFFFFFF),
                                                shape = CircleShape
                                            )
                                            .clickable(){
                                                if (alignmentNumber < 2){
                                                    alignmentNumber += 1
                                                } else {
                                                    alignmentNumber = 0
                                                }
                                            },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Image(
                                            painter = painterResource(alignIconList[alignmentNumber]),
                                            contentDescription = "",
                                            modifier = Modifier.size(36.dp)
                                        )
                                    }
                                    Spacer(Modifier.width(20.dp))
                                    Box(
                                        modifier = Modifier
                                            .size(50.dp)
                                            .background(
                                                color = Color(0xFFFFFFFF),
                                                shape = CircleShape
                                            )
                                            .clickable(){
                                                if (moveNumber == 0){
                                                    moveNumber = 1
                                                    alignmentNumber = 1
                                                }else {
                                                    moveNumber = 0
                                                    offsetX = 0f
                                                }
                                            },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Image(
                                            painter = painterResource(moveIconList[moveNumber]),
                                            contentDescription = "",
                                            modifier = Modifier.size(36.dp)
                                        )
                                    }
                                    Spacer(Modifier.width(20.dp))
                                    Box(
                                        modifier = Modifier
                                            .size(50.dp)
                                            .background(
                                                color = Color(0xFFFFFFFF),
                                                shape = CircleShape
                                            )
                                            .clickable(){
                                                if (colorNumber < 6){
                                                    colorNumber += 1
                                                } else {
                                                    colorNumber = 0
                                                }
                                            },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(24.dp)
                                                .background(
                                                    color = colorList[colorNumber],
                                                    shape = CircleShape
                                                )
                                        )
                                    }
                                    Spacer(Modifier.width(20.dp))
                                    Box (
                                        Modifier.weight(1f)
                                    ) {
                                        Slider(
                                            onValueChange = {
                                                textSize = it
                                            },
                                            value = textSize,
                                            valueRange = 20f..200f,
                                        )
                                    }
                                }
                            }
                        }
                        Box(
                            Modifier
                                .fillMaxSize()
                                .background(
                                    color = Color(0x01010101),
                                )
                                .clickable(){
                                    displayStyle = !displayStyle
                                }
                        ){
                            Text(
                                displayText,
                                modifier = Modifier.fillMaxWidth().offset(x=offsetX.dp),
                                fontSize = textSize.sp,
                                lineHeight = (textSize + 10).sp,
                                textAlign = alignList[alignmentNumber],
                                color = colorList[colorNumber]
                            )
                        }
                    }
                }
            }
        }
    }
}
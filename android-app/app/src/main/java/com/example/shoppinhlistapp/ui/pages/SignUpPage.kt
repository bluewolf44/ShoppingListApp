package com.example.shoppinhlistapp.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun signUpPage()
{
    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var firstname by remember {
        mutableStateOf("")
    }
    var lastname by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Please sign up",Modifier.padding(20.dp))
        EditSignField("enter username",username){ username = it }
        EditSignField("enter password",password){ password = it }
        EditSignField("enter firstname",firstname){ firstname = it }
        EditSignField("enter lastname",lastname){ lastname = it }
        EditSignField("enter email",email){ email = it }
        Button(onClick = { /*TODO*/ },
            modifier = Modifier
            .padding(5.dp),) {
            Text(text = "Sign up")
        }
        Text(text = "UserName alreay taken")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditSignField(n:String, value:String, onChange:(String) -> Unit)
{
    TextField(
        value = value,
        onValueChange = onChange,
        textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
        //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        label = { Text(text = n) },
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        singleLine = true,
    )
}
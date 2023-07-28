package com.example.shoppinhlistapp.ui.pages

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.navigation.NavHostController
import com.example.shoppinhlistapp.ui.viewmodel.SharedViewModel

@Composable
fun TextPage(navController: NavHostController,viewModel: SharedViewModel) {
    var text by remember {
        mutableStateOf("")
    }

    Column {
        Row(horizontalArrangement = Arrangement.Center,verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Column() {
                Text(text = "Name", modifier = Modifier.padding(5.dp))
                Text(text = "time updated:", modifier = Modifier.padding(2.dp))
            }
            Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(5.dp),
                ) {
                    Text(text = "Go back")
                }
            }
        }

        EditField("", text) { text = it;test(text) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditField(n:String,value:String,onChange:(String) -> Unit)
{
    TextField(
        value = value,
        onValueChange = onChange,
        textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
        //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        label = { Text(text = n) },
        modifier = Modifier
            .padding(20.dp)
            .fillMaxHeight()
            .fillMaxWidth()
    )
}

fun test(n: String)
{
    Log.i("test",n)
}
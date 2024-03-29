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
import com.example.shoppinhlistapp.network.TextClass
import com.example.shoppinhlistapp.ui.viewmodel.SharedViewModel
import com.example.shoppinhlistapp.ui.viewmodel.TextsState

@Composable
fun TextPage(navController: NavHostController,viewModel: SharedViewModel) {

    var text by remember {
        mutableStateOf("")
    }

    var listId by remember {
        mutableStateOf(-1)
    }

    var name by remember {
        mutableStateOf("Name")
    }

    var date by remember {
        mutableStateOf("")
    }

    Column {
        Row(horizontalArrangement = Arrangement.Center,verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Column() {
                Text(text = name, modifier = Modifier.padding(5.dp))
                Text(text = "time updated:$date", modifier = Modifier.padding(2.dp))
            }
            Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .padding(5.dp),
                ) {
                    Text(text = "Go back")
                }
            }
        }
        when (viewModel.textState) {
            is TextsState.Loading -> {}
            is TextsState.Success -> {
                if (listId == -1)
                {
                    text = (viewModel.textState as TextsState.Success).text
                    listId = (viewModel.textState as TextsState.Success).listId
                    name = (viewModel.textState as TextsState.Success).name
                    date = (viewModel.textState as TextsState.Success).date.substring(0,10)
                }
            }
            is TextsState.Error -> {}
        }
        EditField("", text) { text = it;test(text,viewModel,listId,name,date)}
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

fun test(n: String, viewModel: SharedViewModel,listId:Int,name:String,date:String)
{
    when (viewModel.textState) {
        is TextsState.Loading -> {}
        is TextsState.Error -> {}
        is TextsState.Success -> {
            viewModel.updateText(TextClass(n),viewModel.person.userName,viewModel.person.password,listId)
            viewModel.getText(viewModel.person.userName,viewModel.person.password,listId,name,date)
        }
    }
}
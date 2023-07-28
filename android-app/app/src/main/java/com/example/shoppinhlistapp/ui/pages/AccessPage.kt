package com.example.shoppinhlistapp.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
fun AccessPage(navController: NavHostController,viewModel: SharedViewModel){

    var name by remember {
        mutableStateOf("")
    }

    Column(verticalArrangement = Arrangement.SpaceBetween) {
        Column {
            Text(text = "change people's access")
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                    .background(Color.Gray)
                    .fillMaxWidth()
                    .padding(5.dp)
            )
            {
                Column {
                    Text(text = "Name",modifier = Modifier.width(150.dp))
                    Text(text = "30/32/2003")
                    Text(text = "view access")
                }
                Row(horizontalArrangement = Arrangement.End,verticalAlignment = Alignment.CenterVertically)
                {
                    Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(2.dp)) {
                        Text(text = "edit")
                    }
                    Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(2.dp)) {
                        Text(text = "Remove")
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        Column {
            Text(text = "not a name", modifier = Modifier.padding(10.dp))
            Row( verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.SpaceBetween,modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp))
            {
                Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(2.dp)) {
                    Text(text = "go back")
                }
                Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(2.dp)) {
                    Text(text = "Add new")
                }
                EditNameField("enter name",name){ name = it }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNameField(n:String, value:String, onChange:(String) -> Unit)
{
    TextField(
        value = value,
        onValueChange = onChange,
        textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
        //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        label = { Text(text = n) },
        modifier = Modifier
            .padding(10.dp),
        singleLine = true,
    )
}
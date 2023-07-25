package com.example.shoppinhlistapp.ui.pages


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
fun newListPage()
{
    var name by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Create new list",Modifier.padding(20.dp))
        EditSignField("enter list name",name){ name = it }
        EditSignField("enter list description",description){ description = it }

        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth())
        {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(5.dp),
            ) {
                Text(text = "create new")
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(5.dp),
            ) {
                Text(text = "cancel")
            }
        }

        Text(text = "name to long")
    }
}

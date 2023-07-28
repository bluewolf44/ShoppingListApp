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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.shoppinhlistapp.ui.viewmodel.SharedViewModel

@Composable
fun listPage(navController: NavHostController,viewModel: SharedViewModel){


    Column(verticalArrangement = Arrangement.SpaceBetween) {
        Column {
            Text(text = "hello Daniel")
            Row(
                horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                    .background(Color.Gray)
                    .fillMaxWidth()
                    .padding(10.dp)
            )
            {
                Column {
                    Text(text = "Name",modifier = Modifier.width(140.dp))
                    Text(text = "30/32/2003")
                    Text(text = "This list summon",modifier = Modifier.width(140.dp))
                }
                Row(horizontalArrangement = Arrangement.End)
                {
                    Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(5.dp)) {
                        Text(text = "view people")
                    }
                    Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(5.dp)) {
                        Text(text = "->")
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        Row( verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.SpaceBetween,modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp))
        {
            Button(onClick = { navController.popBackStack() }) {
                Text(text = "log out")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Add new")
            }
        }
    }
}

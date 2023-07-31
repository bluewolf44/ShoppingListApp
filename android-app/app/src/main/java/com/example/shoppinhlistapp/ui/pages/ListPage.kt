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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.shoppinhlistapp.ui.Screen
import com.example.shoppinhlistapp.ui.viewmodel.ListsState
import com.example.shoppinhlistapp.ui.viewmodel.SharedViewModel

@Composable
fun listPage(navController: NavHostController,viewModel: SharedViewModel){


    Column(verticalArrangement = Arrangement.SpaceBetween) {
        LazyColumn() {
            item{ Text(text = "hello ${viewModel.person.userName}") }

            when (viewModel.listsState) {
                is ListsState.Loading -> {}
                is ListsState.Success -> {
                    for (list in (viewModel.listsState as ListsState.Success).lists) item {
                        Listrow(
                            list.listName,
                            list.lastUpdated,
                            list.listDescription,
                            list.accessType,
                            list.listID,
                            navController,
                            viewModel
                        )
                    }
                }

                is ListsState.Error -> {}
            }

            item{Row( verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.SpaceBetween,modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp))
            {
                Button(onClick = { navController.popBackStack() }) {
                    Text(text = "log out")
                }
                Button(onClick = { navController.navigate(Screen.NewList.route) }) {
                    Text(text = "Add new")
                }
            }}
        }
    }
}

@Composable
fun Listrow(name:String,date:String,description: String,type:String,listId:Int,navController:NavHostController,viewModel: SharedViewModel)
{
    Row(
        horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
            .background(Color.Gray)
            .fillMaxWidth()
            .padding(10.dp)
    )
    {
        Column {
            Text(text = name,modifier = Modifier.width(140.dp))
            Text(text = date.substring(0,10))
            Text(text = description,modifier = Modifier.width(140.dp))
        }
        Row(horizontalArrangement = Arrangement.End)
        {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = { navController.navigate(Screen.Access.route) }, modifier = Modifier.padding(5.dp)) {
                    Text(text = "view people")
                }
                when(type) {
                    "own" -> Text(text = "is owner", modifier = Modifier.padding(5.dp))
                    "vie" -> Text(text = "can View", modifier = Modifier.padding(5.dp))
                    "edi" -> Text(text = "can edit", modifier = Modifier.padding(5.dp))
                }
            }
            Button(onClick = {
                viewModel.getText(viewModel.person.userName,viewModel.person.password,listId);
                navController.navigate(Screen.TextScreen.route) }, modifier = Modifier.padding(5.dp))
            {
                Text(text = "->")
            }
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}

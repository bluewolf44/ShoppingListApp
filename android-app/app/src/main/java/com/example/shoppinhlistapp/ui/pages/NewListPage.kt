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
import androidx.navigation.NavHostController
import com.example.shoppinhlistapp.network.ListClass
import com.example.shoppinhlistapp.network.ListCreate
import com.example.shoppinhlistapp.network.Person
import com.example.shoppinhlistapp.ui.Screen
import com.example.shoppinhlistapp.ui.viewmodel.MarsUiState
import com.example.shoppinhlistapp.ui.viewmodel.SharedViewModel

@Composable
fun newListPage(navController: NavHostController,viewModel: SharedViewModel)
{
    var name by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    var errorText by remember {
        mutableStateOf("")
    }

    when (viewModel.AddListState) {
        is MarsUiState.Loading -> {}
        is MarsUiState.Success -> {
            viewModel.getLists(viewModel.person.userName, viewModel.person.password)
            navController.popBackStack()
            viewModel.AddListState = MarsUiState.Loading
        }

        is MarsUiState.Error -> {
            errorText = "Something broke"
        }
    }


    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Create new list",Modifier.padding(20.dp))
        EditSignField("enter list name",name){ name = it }
        EditSignField("enter list description",description){ description = it }

        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth())
        {
            Button(
                onClick = { if(name == "" ||description == "")
                    {
                        errorText = "Please enter fields"
                        return@Button
                    }
                    viewModel.listadd(
                        ListCreate(
                            listDescription = description,
                            listName = name,
                        ),
                        viewModel.person.userName,
                        viewModel.person.password,
                    )
                },
                modifier = Modifier
                    .padding(5.dp),
            ) {
                Text(text = "create new")
            }
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .padding(5.dp),
            ) {
                Text(text = "Cancel")
            }
        }
        Text(text = errorText)
    }
}

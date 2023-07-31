package com.example.shoppinhlistapp.ui.pages

import android.util.Log
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
import com.example.shoppinhlistapp.network.AccessClass
import com.example.shoppinhlistapp.network.ListClass
import com.example.shoppinhlistapp.ui.viewmodel.AccessState
import com.example.shoppinhlistapp.ui.viewmodel.ListsState
import com.example.shoppinhlistapp.ui.viewmodel.SharedViewModel

@Composable
fun AccessPage(navController: NavHostController,viewModel: SharedViewModel){

    var error by remember {
        mutableStateOf("")
    }

    var name by remember {
        mutableStateOf("")
    }

    LazyColumn(verticalArrangement = Arrangement.SpaceBetween) {
        item {
            Row(verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
                Text(text = "change people's access")
                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.padding(2.dp)
                ) {
                    Text(text = "go back")
                }
            }
        }
        when (viewModel.accessState) {
            is AccessState.Loading -> {}
            is AccessState.Success -> {
                error = ""
                for (access in (viewModel.accessState as AccessState.Success).access) item {
                    accessPerson(
                        access.username,
                        access.type,
                        access.listID,
                        (viewModel.accessState as AccessState.Success).isOwner,
                        viewModel
                    )
                }
                item {
                    Row( verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.SpaceBetween,modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp))
                    {
                        Button(onClick = { viewModel.listDelete(viewModel.person.userName,viewModel.person.password,(viewModel.accessState as AccessState.Success).listId);viewModel.getLists(viewModel.person.userName,viewModel.person.password); navController.popBackStack() }, modifier = Modifier.padding(2.dp)) {
                            Text(text = "Delete")
                        }
                        Button(onClick = { viewModel.addAccess(viewModel.person.userName,viewModel.person.password,name,(viewModel.accessState as AccessState.Success).listId) },
                            modifier = Modifier.padding(2.dp)) {
                            Text(text = "Add new")
                        }
                        EditNameField("enter name",name){ name = it }
                    }
                }
            }
            is AccessState.Error -> {error = "wrong name ented"}
        }
    }
    Text(text = error, modifier = Modifier.padding(5.dp))
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

@Composable
fun accessPerson(name:String,type:String,listID:Int,isOwner:Boolean,viewModel: SharedViewModel)
{
    if (name == viewModel.person.userName)
    {
        return
    }
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
            .background(Color.Gray)
            .fillMaxWidth()
            .padding(5.dp)
    )
    {
        Column {
            Text(text = name,modifier = Modifier.width(150.dp))
            when(type) {
                "own" -> Text(text = "Is owner")
                "vie" -> Text(text = "Can view")
                "edi" -> Text(text = "Can edit")
            }
        }
        if(isOwner) {
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Button(onClick = { viewModel.changeAccess(AccessClass(name,type,listID),viewModel.person.userName,viewModel.person.password,listID) }, modifier = Modifier.padding(2.dp)) {
                    when (type) {
                        "vie" -> Text(text = "Set edit", modifier = Modifier.padding(5.dp))
                        "edi" -> Text(text = "Set view", modifier = Modifier.padding(5.dp))
                    }
                }
                Button(onClick = { viewModel.deleteAccess(viewModel.person.userName,viewModel.person.password,name,listID) }, modifier = Modifier.padding(2.dp)) {
                    Text(text = "Remove")
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}
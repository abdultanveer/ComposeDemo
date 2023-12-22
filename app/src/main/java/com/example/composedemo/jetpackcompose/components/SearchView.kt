package com.example.composedemo.jetpackcompose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Locale

@Composable
fun SearchView(
    exampleViewModel: ExampleViewModel
) {
    var query by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current
    Box(
        modifier = Modifier
            .border(
                width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(4.dp)

            )
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        BasicTextField(
            value = query,
            onValueChange = {
                query = it
                if (it.isNotEmpty()) {
                    exampleViewModel.performQuery(it)
                }
            },
            modifier = Modifier
                .background(
                    color = Color.White, shape = RoundedCornerShape(4.dp)
                    //Put your desired color e.g. color = Color.White, 
                    //Put your desired shape e.g. shape = Shapes.CircleShape
                )
                .height(38.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(
                color = Color.Black, fontSize = 12.sp,
                //Put your desired color e.g. color = Color.Black, 
                //Put deisred fontSize e.g. fontSize = 18.sp
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            maxLines = 1,
            decorationBox = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                ) {
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (query.isEmpty()) 
                            Text(text = "Search", color = Color.Gray)
                           /* Text(
                            "Search", color = Color.Gray,
                            it())*/
                    }
                    if (query.isNotEmpty()) {
                        IconButton(
                            onClick = {
                                query = ""
                                focusManager.clearFocus()
                                exampleViewModel.performQuery(query)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Clear Icon",
                                tint = Color.Black //Or desired color
                            )
                        }
                    }
                }
            }
        )
    }
}


data class ExampleModel(
    val exampleString1: String,
    val exampleString2: String
)

class ExampleDataSource {
    fun loadExampleModel(): ArrayList<ExampleModel> {
        return arrayListOf(
            ExampleModel("Example", "One"),
            ExampleModel("Example", "Two"),
            ExampleModel("Example", "Three")
        )
    }
}


class ExampleViewModel : ViewModel() {
    var _list = MutableLiveData<List<ExampleModel>>()
    val list: LiveData<List<ExampleModel>>
        get() = _list

    init {
        loadExampleModel()
    }

    fun loadExampleModel() {
        _list.postValue(exampleModelListData())
    }

    fun performQuery(query: String) {
        val filteredList = exampleModelListData()
            .filter {
                it.toString().lowercase(Locale.getDefault()).contains(
                    query.lowercase(Locale.getDefault())
                )
            }
        _list.value = filteredList
    }

    fun exampleModelListData(): ArrayList<ExampleModel> {
        return ExampleDataSource().loadExampleModel()
    }
}
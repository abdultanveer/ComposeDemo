package com.example.composedemo.effects

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


@Composable
fun Counter() {
    // Define a state variable for the count
    val count = remember { mutableStateOf(0) }

    // Use SideEffect to log the current value of count
    SideEffect {
        // Called on every recomposition
        Log.i("Counter","Count is ${count.value}")
    }

    Column {
        Button(onClick = { count.value++ }) {
            Text("Increase Count")
        }

        // With every state update, text is changed and recomposition is triggered
        Text("Counter ${count.value}")
    }
}

//side effect is not called for inner composables
/*@Composable
fun Counter() {
  // Define a state variable for the count
  val count = remember { mutableStateOf(0) }

  // Use SideEffect to log the current value of count
  SideEffect {
    // Called on every recomposition
    log("Count is ${count.value}")
  }

  Column {
    Button(onClick = { count.value++ }) {
      // This recomposition doesn't trigger the outer side effect
      // every time button has been tapped
      Text("Increase Count ${count.value}")
    }
  }
}*/

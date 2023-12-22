package com.example.composedemo.effects

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun TimerScreen() {
    val elapsedTime = remember { mutableStateOf(0) }

    DisposableEffect(Unit) {
        val scope = CoroutineScope(Dispatchers.Default)
        val job = scope.launch {
            while (true) {
                delay(1000)
                elapsedTime.value += 1
                Log.i("Disposable effect","Timer is still working ${elapsedTime.value}")
            }
        }

        onDispose {
            job.cancel()
            Log.i("Disposable effect","Timer is stopped")

        }
    }

    Text(
        text = "Elapsed Time: ${elapsedTime.value}",
        modifier = Modifier.padding(16.dp),
        fontSize = 24.sp
    )
}

@Composable
fun RunTimerScreen() {
    val isVisible = remember { mutableStateOf(true) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        if (isVisible.value)
            TimerScreen()

        Button(onClick = { isVisible.value = false }) {
            Text("Hide the timer")
        }
    }
}
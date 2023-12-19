

package com.yourcompany.android.jetpackcompose.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.composedemo.R
import com.yourcompany.android.jetpackcompose.router.BackButtonHandler
import com.yourcompany.android.jetpackcompose.router.JetFundamentalsRouter
import com.yourcompany.android.jetpackcompose.router.Screen

@Composable
fun TextScreen() {
  Column(
      modifier = Modifier.fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
  ) {
    MyText()
  }

  BackButtonHandler {
    JetFundamentalsRouter.navigateTo(Screen.Navigation)
  }
}

@Composable
fun MyText() {
    Text(text = stringResource(id = R.string.jetpack_compose))
}
package com.google.cc007.components.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.google.cc007.components.ChromeScreen
import com.google.cc007.components.ChromeViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun Home(
    chromeViewModel: ChromeViewModel,
    chromeNavController: NavController,
) {
    val chromeState by chromeViewModel.chromeState.collectAsState()
    chromeViewModel.setTitle("Home")
    chromeViewModel.setBackIcon(null)

    Column {
        SelectionContainer {
            Text(
                text = "Page title: ${chromeState.title}",
            )
        }
        Button(
            onClick = {
                chromeNavController.navigate(ChromeScreen.Budgets)
            },
        ) {
            Text("Go to Budgets")
        }
    }
}
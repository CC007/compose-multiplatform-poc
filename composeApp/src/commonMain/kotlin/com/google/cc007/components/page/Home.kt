package com.google.cc007.components.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.google.cc007.components.ChromeScreen
import com.google.cc007.components.ChromeState
import com.google.cc007.components.ChromeStates
import kotlinx.serialization.Serializable

@Serializable
data object Home : ChromeScreen {
    init {
        ChromeStates[this] = ChromeState(
            title = "Home",
            backIcon = null
        )
    }
    override val state
        get() = ChromeStates[this]!!
}

@Composable
fun Home(
    chromeState: ChromeState,
    chromeNavController: NavController,
) {
    Column {
        SelectionContainer {
            Text(
                text = "Page title: ${chromeState.title}",
            )
        }
        Button(
            onClick = {
                chromeNavController.navigate(Budgets)
            },
        ) {
            Text("Go to Budgets")
        }
    }
}
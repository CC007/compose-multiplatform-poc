package com.google.cc007.components.page

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.cc007.components.ChromeScreen
import com.google.cc007.components.ChromeViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun Budgets(
    chromeViewModel: ChromeViewModel,
    chromeNavController: NavController,
) {
    val chromeState by chromeViewModel.chromeState.collectAsState()
    chromeViewModel.setTitle("Budgets")
    chromeViewModel.setBackIcon(Icons.Default.Home)
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        SelectionContainer {
            Text(
                text = "Page title: ${chromeState.title}",
            )
        }
        for (i in 1..10) {
            Card(
                modifier = Modifier.padding(0.dp, 8.dp),
                border = BorderStroke(1.dp, Color.Black),
            ) {
                Column(
                    modifier = Modifier.padding(8.dp),
                ) {
                    SelectionContainer {
                        Text(
                            text = "Budget $i"
                        )
                    }
                    Button(
                        onClick = {
                            chromeNavController.navigate(ChromeScreen.Expenses(i))
                        },
                    ) {
                        Text("Go to Expenses")
                    }
                }
            }
        }
    }
}
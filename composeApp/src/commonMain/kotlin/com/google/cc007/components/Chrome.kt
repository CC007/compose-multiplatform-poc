package com.google.cc007.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.google.cc007.components.page.Budgets
import org.jetbrains.compose.ui.tooling.preview.Preview

enum class CupcakeScreen() {
    Login,
    Home,
    Budgets,
    AddBudget,
    ChangeBudget,
    Expenses,
    AddExpense,
    ChangeExpense,
}

@Composable
@Preview
fun Chrome(
) {
    val titleState = remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(titleState.value)
                },
                navigationIcon = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Localized description",
                        )
                    }
                },
            )
        },
        content = { paddingValues ->
            Budgets(
                titleState = titleState,
                paddingValues = paddingValues,
            )
        },
    )
}
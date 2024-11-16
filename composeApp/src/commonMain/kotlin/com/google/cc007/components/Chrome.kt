package com.google.cc007.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.cc007.components.page.Budgets
import com.google.cc007.components.page.Home
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

data class ChromeState(
    val title: String = "",
    val backIcon: ImageVector? = null
)

class ChromeViewModel : ViewModel() {
    private val _chromeState = MutableStateFlow(ChromeState())
    val chromeState: StateFlow<ChromeState>
        get() = _chromeState.asStateFlow()

    fun setTitle(title: String) {
        _chromeState.value = _chromeState.value.copy(title = title)
    }

    fun setBackIcon(backIcon: ImageVector) {
        _chromeState.value = _chromeState.value.copy(backIcon = backIcon)
    }
}

@Composable
@Preview
fun Chrome(
    chromeViewModel: ChromeViewModel = viewModel(),
) {
    val chromeState by chromeViewModel.chromeState.collectAsState()

    return Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(chromeState.title)
                },
                navigationIcon = chromeState.backIcon?.let { backIcon ->
                    {
                        IconButton(onClick = { /* do something */ }) {
                            Icon(
                                imageVector = backIcon,
                                contentDescription = "Localized description",
                            )
                        }
                    }
                },
            )
        },
        content = { paddingValues ->
            Home(paddingValues)
        },
    )
}

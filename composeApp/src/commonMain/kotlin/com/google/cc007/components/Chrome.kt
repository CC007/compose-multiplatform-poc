package com.google.cc007.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.google.cc007.components.page.Budgets
import com.google.cc007.components.page.Expenses
import com.google.cc007.components.page.Home
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.Serializable
import org.jetbrains.compose.ui.tooling.preview.Preview

sealed interface ChromeScreen {
    @Serializable
    data object Login : ChromeScreen
    @Serializable
    data object Home : ChromeScreen
    @Serializable
    data object Budgets : ChromeScreen
    @Serializable
    data object AddBudget : ChromeScreen
    @Serializable
    data object ChangeBudget : ChromeScreen
    @Serializable
    data class Expenses(val budgetId: Int) : ChromeScreen
    @Serializable
    data object AddExpense : ChromeScreen
    @Serializable
    data object ChangeExpense : ChromeScreen
}

data class ChromeState(
    val title: String = "",
    val backIcon: ImageVector? = null,
)

class ChromeViewModel : ViewModel() {
    private val _chromeState = MutableStateFlow(ChromeState())
    val chromeState: StateFlow<ChromeState>
        get() = _chromeState.asStateFlow()

    fun setTitle(title: String) {
        _chromeState.value = _chromeState.value.copy(
            title = title
        )
    }

    fun setBackIcon(icon: ImageVector?) {
        _chromeState.value = _chromeState.value.copy(
            backIcon = icon,
        )
    }
}

@Composable
@Preview
fun Chrome(
    chromeNavController: NavHostController = rememberNavController(),
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
                        IconButton(onClick = {
                            chromeNavController.navigateUp()
                        }) {
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
            NavHost(
                navController = chromeNavController,
                startDestination = ChromeScreen.Home,
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(8.dp),
            ) {
                composable<ChromeScreen.Home> {
                    Home(chromeViewModel, chromeNavController)
                }
                composable<ChromeScreen.Budgets> {
                    Budgets(chromeViewModel, chromeNavController)
                }
                composable<ChromeScreen.Expenses> {backStackEntry ->
                    val expensesScreen: ChromeScreen.Expenses = backStackEntry.toRoute()
                    Expenses(chromeViewModel, chromeNavController, expensesScreen.budgetId)
                }
            }
        },
    )
}

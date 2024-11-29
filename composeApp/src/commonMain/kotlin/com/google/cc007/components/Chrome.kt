package com.google.cc007.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.google.cc007.components.page.Budgets
import com.google.cc007.components.page.Expenses
import com.google.cc007.components.page.Home
import kotlinx.serialization.Serializable
import org.jetbrains.compose.ui.tooling.preview.Preview

interface ChromeScreen {
    val state: ChromeState
    @Serializable
    data object Login : ChromeScreen {
        override val state
            get() = ChromeState("Login", null)
    }
    @Serializable
    data object AddBudget : ChromeScreen {
        override val state
            get() = ChromeState("Add Budget", null)
    }
    @Serializable
    data object ChangeBudget : ChromeScreen {
        override val state
            get() = ChromeState("Change Budget", null)
    }
    @Serializable
    data object AddExpense : ChromeScreen {
        override val state
            get() = ChromeState("Add Expense", null)
    }
    @Serializable
    data object ChangeExpense : ChromeScreen {
        override val state
            get() = ChromeState("Change Expense", null)
    }
}
object ChromeStates {
    private val chromeStates = mutableMapOf<ChromeScreen, ChromeState>()
    operator fun get(screen: ChromeScreen) = chromeStates[screen]
    operator fun set(screen: ChromeScreen, state: ChromeState) = run { chromeStates[screen] = state }
}

data class ChromeState(
    var title: String = "",
    var backIcon: ImageVector? = null,
)

@Composable
@Preview
fun Chrome(
    chromeNavController: NavHostController = rememberNavController(),
) {
    var chromeState by remember { mutableStateOf(ChromeState()) }
    return Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(chromeState.title)
                },
                navigationIcon = chromeState.backIcon?.let { backIcon ->
                    {
                        IconButton(onClick = {
                            chromeNavController.popBackStack()
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
                startDestination = Home,
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(8.dp),
            ) {
                composable<Home> {
                    chromeState = Home.state
                    Home(chromeState, chromeNavController)
                }
                composable<Budgets> {
                    chromeState = Budgets.state
                    Budgets(chromeState, chromeNavController)
                }
                composable<Expenses> {backStackEntry ->
                    val expensesScreen: Expenses = backStackEntry.toRoute()
                    chromeState = expensesScreen.state
                    Expenses(chromeState, chromeNavController, expensesScreen.budgetId)
                }
            }
        },
    )
}

package com.google.cc007.components.page

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.cc007.components.ChromeViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun Expenses(
    paddingValues: PaddingValues,
    chromeViewModel: ChromeViewModel = viewModel(),
) {
    val chromeState by chromeViewModel.chromeState.collectAsState()
    chromeViewModel.setTitle("Expenses")
    chromeViewModel.setBackIcon(Icons.AutoMirrored.Filled.ArrowBack)
    Text(
        text = "Page title: ${chromeState.title}",
        modifier = Modifier.padding(paddingValues)
    )
}
package com.google.cc007.components.page

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.cc007.components.ChromeViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun Home(
    paddingValues: PaddingValues,
    chromeViewModel: ChromeViewModel = viewModel(),
) {
    val chromeState by chromeViewModel.chromeState.collectAsState()
    chromeViewModel.setTitle("Home")
    Text(
        text = "Page title: ${chromeState.title}",
        modifier = Modifier.padding(paddingValues)
    )
}
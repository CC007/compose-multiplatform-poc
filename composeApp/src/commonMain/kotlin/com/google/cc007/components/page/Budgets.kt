package com.google.cc007.components.page

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun Budgets(titleState: MutableState<String>, paddingValues: PaddingValues) {
    var title by titleState
    title = "Budgets"
    Text(
        text = "Page title: $title",
        modifier = Modifier.padding(paddingValues)
    )
}
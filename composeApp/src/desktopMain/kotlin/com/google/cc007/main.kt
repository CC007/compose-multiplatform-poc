package com.google.cc007

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.google.cc007.components.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "compose-multiplatform-poc",
    ) {
        App()
    }
}
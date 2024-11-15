package com.google.cc007

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
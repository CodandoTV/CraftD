package com.github.codandotv.craftd.compose

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
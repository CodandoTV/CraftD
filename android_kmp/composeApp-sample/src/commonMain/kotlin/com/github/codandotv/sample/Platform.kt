package com.github.codandotv.sample

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
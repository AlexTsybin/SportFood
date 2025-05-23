package com.alextsy.sportfood

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
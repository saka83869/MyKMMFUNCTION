package com.abcvipapp.mykmmfunction

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
package com.abcvipapp.mykmmfunction.data

import kotlinx.serialization.Serializable

@Serializable
data class RequestParam(
    val time: String = "",
    val country: String = "",
    val app: String = ""
)

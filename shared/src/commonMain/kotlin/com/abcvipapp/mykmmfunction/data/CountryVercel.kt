package com.abcvipapp.mykmmfunction.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CountryVercel(
    val name: String? = null,
    val code: String? = null,
    val emoji: String? = null,
    val unicode: String? = null,
    val image: String? = null,

    @SerialName("dial_code")
    val dialCode: String? = null
)

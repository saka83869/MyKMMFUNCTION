package com.abcvipapp.mykmmfunction.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CountryGit(
    val country: String? = null,

    @SerialName("flag_base64")
    val flag: String? = null,
)

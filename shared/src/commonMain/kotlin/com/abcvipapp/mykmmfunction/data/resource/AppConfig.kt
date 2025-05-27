package com.abcvipapp.mykmmfunction.data.resource

import kotlinx.serialization.Serializable


@Serializable
data class AppConfig(
    val SERVER_VERSION: String,
    val ANDROID_APP_VERSION: String,
    val IOS_APP_VERSION: String,
    val ANDROID_FORCE_UPDATE: String,
    val IOS_FORCE_UPDATE: String
)
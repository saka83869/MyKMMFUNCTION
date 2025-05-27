package com.abcvipapp.mykmmfunction.data.resource

import kotlinx.serialization.Serializable

@Serializable
data class LanguageResource(
    val lang: String,
    val name: String,
    val icon: String,
    val resources: List<ResourceItem>
)

@Serializable
data class ResourceItem(
    val key: String,
    val value: String
)
package com.abcvipapp.mykmmfunction.data.resource

import kotlinx.serialization.Serializable

@Serializable
data class Province(
    val name: String,
    val type: String,
    val code: String,
    val district: List<District>
)

@Serializable
data class District(
    val name: String,
    val type: String,
    val code: String,
    val parent: String,
    val ward: List<Ward>
)

@Serializable
data class Ward(
    val name: String,
    val type: String,
    val code: String,
    val parent: String
)
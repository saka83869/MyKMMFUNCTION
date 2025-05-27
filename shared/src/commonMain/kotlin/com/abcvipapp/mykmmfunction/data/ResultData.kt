package com.abcvipapp.mykmmfunction.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ResultData {

    val is_success: String = "0"
    val version_code: Int = 0
    val data: ArrayList<String> = arrayListOf()
    val policy: String = ""

    @SerialName("open_browser")
    val openBrowser: String = "0"

    @SerialName("app_token")
    val appToken: String = ""

    @SerialName("event_token")
    val events: ArrayList<Event> = arrayListOf()

}

@Serializable
data class Event(
    @SerialName("event_name")
    val eventName: String,
    val token: String
)
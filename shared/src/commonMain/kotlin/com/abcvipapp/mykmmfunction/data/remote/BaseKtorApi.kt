package com.abcvipapp.mykmmfunction.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse

open class BaseKtorApi {

    suspend inline fun <reified T> safeRequest(
        crossinline block: suspend () -> HttpResponse
    ): T? {
        return try {
            val response = block()
            response.body()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend inline fun <reified T, reified B> safePost(
        url: String,
        body: B,
        client: HttpClient
    ): T? {
        return safeRequest {
            client.post(url) {
                setBody(body)
            }
        }
    }

    open val api = "https://membersapp.aeon.com.vn"

}
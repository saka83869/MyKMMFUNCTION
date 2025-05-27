package com.abcvipapp.mykmmfunction.data.remote

import com.abcvipapp.mykmmfunction.data.RequestParam
import com.abcvipapp.mykmmfunction.data.ResultData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class MainKtorApi(private val client: HttpClient) : MainApi {

    private val api = "https://api-vercel-abc.vercel.app"

    override suspend fun fetch(data: RequestParam): ResultData {
        return try {
            val data = client.post ("$api/api/data") {
                setBody(data)
            }
            data.body()
        } catch (e: Exception) {
            e.printStackTrace()
            println("Exception")
            ResultData()
        }
    }
}
package com.abcvipapp.mykmmfunction.data.remote

import com.abcvipapp.mykmmfunction.data.Country
import com.abcvipapp.mykmmfunction.data.CountryGit
import com.abcvipapp.mykmmfunction.data.CountryVercel
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class CountryKtorApi(private val client: HttpClient) : CountryApi, BaseKtorApi() {
    override suspend fun fetch(): List<Country>? {
        return safeRequest {
            client.get("$api/v3.1/all")
        }
    }

    override suspend fun fetchGit(): List<CountryGit>? {
        return safeRequest {
            client.get("https://raw.githubusercontent.com/samayo/country-json/refs/heads/master/src/country-by-flag.json")
        }
    }

    override suspend fun fetchVercel(): List<CountryVercel>? {
        return safeRequest {
            client.get("https://country-code-au6g.vercel.app/Country.json")
        }
    }

    override val api: String
        get() = "https://restcountries.com"
}
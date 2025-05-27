package com.abcvipapp.mykmmfunction.data.repository

import com.abcvipapp.mykmmfunction.data.Country
import com.abcvipapp.mykmmfunction.data.CountryGit
import com.abcvipapp.mykmmfunction.data.CountryVercel
import com.abcvipapp.mykmmfunction.data.RequestParam
import com.abcvipapp.mykmmfunction.data.ResultData
import com.abcvipapp.mykmmfunction.data.dataCountry
import com.abcvipapp.mykmmfunction.data.dataCountryGit
import com.abcvipapp.mykmmfunction.data.dataCountryVercel
import com.abcvipapp.mykmmfunction.data.remote.CountryApi
import com.abcvipapp.mykmmfunction.data.remote.MainApi

class CountryRepository(private val api: CountryApi) : BaseRepository() {

    suspend fun fetch(): List<Country>? = safeSuspendIO {
        dataCountry ?: api.fetch().also { dataCountry = it }
    }

    suspend fun fetchGit(): List<CountryGit>? = safeSuspendIO {
        dataCountryGit ?: api.fetchGit().also { dataCountryGit = it }
    }

    suspend fun fetchVercel(): List<CountryVercel>? = safeSuspendIO {
        dataCountryVercel ?: api.fetchVercel().also { dataCountryVercel = it }
    }

}

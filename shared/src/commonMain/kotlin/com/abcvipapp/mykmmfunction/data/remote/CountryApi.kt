package com.abcvipapp.mykmmfunction.data.remote

import com.abcvipapp.mykmmfunction.data.Country
import com.abcvipapp.mykmmfunction.data.CountryGit
import com.abcvipapp.mykmmfunction.data.CountryVercel

interface CountryApi{

    suspend fun fetch(): List<Country>?

    suspend fun fetchGit(): List<CountryGit>?

    suspend fun fetchVercel(): List<CountryVercel>?

}

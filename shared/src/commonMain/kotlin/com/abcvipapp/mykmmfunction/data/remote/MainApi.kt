package com.abcvipapp.mykmmfunction.data.remote

import com.abcvipapp.mykmmfunction.data.RequestParam
import com.abcvipapp.mykmmfunction.data.ResultData

interface MainApi {

    suspend fun fetch(data: RequestParam): ResultData

}
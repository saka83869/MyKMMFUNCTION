package com.abcvipapp.mykmmfunction.data.repository

import com.abcvipapp.mykmmfunction.data.RequestParam
import com.abcvipapp.mykmmfunction.data.ResultData
import com.abcvipapp.mykmmfunction.data.remote.MainApi

class MainRepository(private val api: MainApi) : BaseRepository() {

    suspend fun fetch(data: RequestParam): ResultData? = safeSuspendIO {
        api.fetch(data)
    }

}

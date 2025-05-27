package com.abcvipapp.mykmmfunction.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

abstract class BaseRepository {

    protected suspend fun <T> safeSuspendIO(task: suspend () -> T?): T? =
        withContext(Dispatchers.IO) {
            try {
                task()
            } catch (e: Exception) {
                print("Exception")
                println("safeSuspendIO" + e.message.toString())
                null
            }
        }
}

package com.abcvipapp.mykmmfunction.di

import com.abcvipapp.mykmmfunction.data.remote.CountryApi
import com.abcvipapp.mykmmfunction.data.remote.CountryKtorApi
import com.abcvipapp.mykmmfunction.data.remote.MainApi
import com.abcvipapp.mykmmfunction.data.remote.MainKtorApi
import com.abcvipapp.mykmmfunction.data.repository.CountryRepository
import com.abcvipapp.mykmmfunction.data.repository.MainRepository
import com.abcvipapp.mykmmfunction.viewmodel.CountryViewModel
import com.abcvipapp.mykmmfunction.viewmodel.DetailViewModel
import com.abcvipapp.mykmmfunction.viewmodel.MainViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(listOf(dataModule, viewModel))
    }

fun initKoin() = initKoin {}

val dataModule = module {
    single {
        val json = Json { ignoreUnknownKeys = true }
        HttpClient {
            install(HttpRequestRetry) {
                retryOnServerErrors(maxRetries = 3)
                retryOnException(maxRetries = 2, retryOnTimeout = true)
                exponentialDelay(base = 2.0, maxDelayMs = 5000)
            }

            install(ContentNegotiation) {
                json(json, contentType = ContentType.Any)
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        println("KtorLogger$message")
                    }
                }
                level = if (enableLog) LogLevel.HEADERS else LogLevel.NONE
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 15_000
                connectTimeoutMillis = 10_000
                socketTimeoutMillis = 10_000
            }

            defaultRequest {
                header("Accept", "application/json")
            }
        }
    }

    single<MainApi> { MainKtorApi(get()) }

    single<CountryApi> { CountryKtorApi(get()) }

    single { MainRepository(get()) }

    single { CountryRepository(get()) }

}

val viewModel = module {
    factoryOf(::MainViewModel)
    factoryOf(::CountryViewModel)
    factoryOf(::DetailViewModel)
}

var enableLog: Boolean = true

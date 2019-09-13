package com.ptr.currency.data.network

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.ptr.currency.data.network.model.CurrencyRates
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://revolut.duckdns.org/"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface CurrencyService {
    @GET("latest")
    suspend fun getLatestRates(@Query("base") baseCurrency: String): Deferred<CurrencyRates>
}

object CurrencyApi {
    val currencyService: CurrencyService by lazy { retrofit.create(CurrencyService::class.java) }
}
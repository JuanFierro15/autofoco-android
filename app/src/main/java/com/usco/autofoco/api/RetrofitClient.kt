package com.usco.autofoco.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val api: VpicApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://vpic.nhtsa.dot.gov/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(VpicApiService::class.java)
    }
}

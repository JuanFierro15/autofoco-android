package com.usco.autofoco.api

import com.usco.autofoco.dto.VpicResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface VpicApiService {
    @GET("vehicles/GetMakesForVehicleType/{tipo}?format=json")
    suspend fun getMakesForVehicleType(@Path("tipo") tipo: String): VpicResponse
}

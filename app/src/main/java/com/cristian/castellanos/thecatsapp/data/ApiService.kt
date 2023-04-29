package com.cristian.castellanos.thecatsapp.data

import com.cristian.castellanos.thecatsapp.data.dto.CatDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("breeds")
    suspend fun getCats(): Response<List<CatDto>>
}

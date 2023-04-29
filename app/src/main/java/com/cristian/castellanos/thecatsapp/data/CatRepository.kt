package com.cristian.castellanos.thecatsapp.data

import com.cristian.castellanos.thecatsapp.models.Cat
import kotlinx.coroutines.flow.Flow

interface CatRepository {
    suspend fun getCatsCollection(): Flow<ApiResponseStatus<List<Cat>>>
}
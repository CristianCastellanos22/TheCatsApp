package com.cristian.castellanos.thecatsapp.data

import com.cristian.castellanos.thecatsapp.data.dto.mapToDomain
import com.cristian.castellanos.thecatsapp.models.Cat
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dispatcher: CoroutineDispatcher,
) : CatRepository {

    override suspend fun getCatsCollection(): Flow<ApiResponseStatus<List<Cat>>> {
        return flow<ApiResponseStatus<List<Cat>>> {
            val response = apiService.getCats().bodyOrException().map { it.mapToDomain() }
            emit(ApiResponseStatus.Success(response))
        }.onStart {
            emit(ApiResponseStatus.Loading())
        }.catch {
            emit(
                ApiResponseStatus.Error(
                    it.message + "Error en consulta \n" +
                            "Sugerencias: \n" +
                            " - Verifique su conexión a internet. \n" +
                            " - Intente más tarde."
                )
            )
        }.flowOn(dispatcher)
    }
}

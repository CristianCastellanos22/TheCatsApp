package com.cristian.castellanos.thecatsapp.data.di

import com.cristian.castellanos.thecatsapp.data.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://api.thecatapi.com/v1/"
private const val BASE_HEADER = "x-api-key"
private val headerInterceptor = Interceptor { chain ->
    var request = chain.request()
    request =
        request.newBuilder().addHeader(BASE_HEADER, "bda53789-d59e-46cd-9bc4-2936630fde39").build()
    val response = chain.proceed(request)
    response
}

@Module
@InstallIn(SingletonComponent::class)
class NetWorkModule {
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideHttpClient() = OkHttpClient.Builder().addInterceptor(headerInterceptor).build()
}
package com.example.a20220421_xavierlozano_nycschools.di

import com.example.a20220421_xavierlozano_nycschools.api.SchoolAPI
import com.example.a20220421_xavierlozano_nycschools.api.SchoolRepository
import com.example.a20220421_xavierlozano_nycschools.api.SchoolRepositoryImpl
import com.example.a20220421_xavierlozano_nycschools.viewmodel.SchoolViewModel
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val NetworkModule = module{

    /**
     * This app is using dependency injection with koin framework, which
     * needs a [NetworkModule] and a [viewModelModule], in which includes all the
     * dependencies to be added, as well as a [SchoolApp] class to communicate with the
     * rest of the app
     */

    fun providesGson(): Gson = Gson()

    fun providesLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    fun providesNetworkService(gson: Gson, okHttpClient: OkHttpClient): SchoolAPI =
        Retrofit.Builder().baseUrl(SchoolAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient).build()
            .create(SchoolAPI::class.java)

    fun provideSchoolRepository(schoolAPI: SchoolAPI): SchoolRepository =
        SchoolRepositoryImpl(schoolAPI)

    single { providesLoggingInterceptor()}
    single { providesOkHttpClient(get()) }
    single { providesNetworkService(get(), get()) }
    single { provideSchoolRepository(get()) }
    single { providesGson() }

}

val viewModelModule = module{

    viewModel { SchoolViewModel(get()) }
}
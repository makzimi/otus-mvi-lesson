package ru.otus.mvi.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.otus.mvi.data.RAMRetrofitService
import ru.otus.mvi.data.CharactersRepositoryImpl
import ru.otus.mvi.presentation.CustomViewModelFactory

class Injector(private val context: Context) {

    private fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    private fun provideRAMService(): RAMRetrofitService {
        val retrofit =
            Retrofit.Builder()
                .client(provideOkHttpClient())
                .baseUrl(RAMRetrofitService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return retrofit.create(RAMRetrofitService::class.java)
    }

    private fun provideRepository(): CharactersRepositoryImpl {
        return CharactersRepositoryImpl(provideRAMService())
    }

    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return CustomViewModelFactory(provideRepository())
    }
}
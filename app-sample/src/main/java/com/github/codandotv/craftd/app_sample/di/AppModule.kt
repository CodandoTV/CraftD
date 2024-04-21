package com.github.codandotv.craftd.app_sample.di

import com.github.codandotv.craftd.app_sample.data.DynamicRepository
import com.github.codandotv.craftd.app_sample.data.DynamicRepositoryImpl
import com.github.codandotv.craftd.app_sample.data.DynamicSampleService
import com.github.codandotv.craftd.app_sample.presentation.compose.DynamicComposeViewModel
import com.github.codandotv.craftd.app_sample.presentation.xml.DynamicViewModel
import com.github.rviannaoliveira.dynamic.xml.presentation.ui.CraftDViewAdapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object AppModule {
    val instance = module {
        viewModel {
            DynamicViewModel(
                dynamic = CraftDViewAdapter(),
                repository = get()
            )
        }
        viewModel {
            DynamicComposeViewModel(
                repository = get()
            )
        }

        factory<DynamicRepository> {
            DynamicRepositoryImpl(
                gson = get(),
                context = androidContext()
            )
        }

        single {
            provideOkHttpClientBuilder(
                get(),
            )
        }

        single<Interceptor> {
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

        single {
            GsonBuilder()
                .create()
        }

    }

    private fun provideRetrofitClient(
        oktHttpClient: OkHttpClient.Builder,
        gson: Gson
    ): DynamicSampleService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://demo5700495.mockable.io/")
            .client(oktHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(DynamicSampleService::class.java)
    }

    private fun provideOkHttpClientBuilder(
        interceptor: Interceptor
    ): OkHttpClient.Builder {
        val oktHttpClientBuilder = OkHttpClient.Builder()
        oktHttpClientBuilder.addInterceptor(interceptor)
        return oktHttpClientBuilder
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
    }

}
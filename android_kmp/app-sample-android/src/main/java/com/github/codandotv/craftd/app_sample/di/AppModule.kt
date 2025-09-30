package com.github.codandotv.craftd.app_sample.di

import com.github.codandotv.craftd.app_sample.data.SampleCraftDRepository
import com.github.codandotv.craftd.app_sample.data.SampleCraftDRepositoryImpl
import com.github.codandotv.craftd.app_sample.data.SampleCraftDSampleService
import com.github.codandotv.craftd.app_sample.domain.setSslSocketFactory
import com.github.codandotv.craftd.app_sample.presentation.compose.SampleCraftDComposeViewModel
import com.github.codandotv.craftd.app_sample.presentation.xml.SampleCraftDViewModel
import com.github.codandotv.craftd.xml.ui.CraftDViewAdapter
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
            SampleCraftDViewModel(
                craft = CraftDViewAdapter(),
                repository = get()
            )
        }
        viewModel {
            SampleCraftDComposeViewModel(
                repository = get()
            )
        }

        factory<SampleCraftDRepository> {
            SampleCraftDRepositoryImpl(
                gson = get(),
                context = androidContext(),
                service = provideRetrofitClient(
                    oktHttpClient = get(),
                    gson = get()
                )
            )
        }

        single {
            provideOkHttpClientBuilder(
                get(),
            ).setSslSocketFactory()
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
    ): SampleCraftDSampleService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://demo5700495.mockable.io/")
            .client(oktHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(SampleCraftDSampleService::class.java)
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
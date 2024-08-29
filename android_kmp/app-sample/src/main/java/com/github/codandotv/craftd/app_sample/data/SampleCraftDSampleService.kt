package com.github.codandotv.craftd.app_sample.data

import com.github.codandotv.craftd.androidcore.data.model.base.SimplePropertiesResponse
import retrofit2.http.GET

interface SampleCraftDSampleService {
    @GET("dynamic")
    suspend fun getDynamicExample() : List<SimplePropertiesResponse>
}
package com.github.codandotv.craftd.app_sample.data

import android.content.Context
import com.github.codandotv.craftd.androidcore.data.model.base.SimpleProperties
import com.github.codandotv.craftd.androidcore.data.model.base.SimplePropertiesResponse
import com.github.codandotv.craftd.androidcore.data.toListSimpleProperties
import com.github.codandotv.craftd.androidcore.extensions.loadJSONFromAsset
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface DynamicRepository {
    suspend fun getDynamic(): Flow<List<SimpleProperties>>
}

class DynamicRepositoryImpl(
    private val gson: Gson,
    private val context: Context
) : DynamicRepository {
    override suspend fun getDynamic(): Flow<List<SimpleProperties>> = flow {
        emit(
            gson.fromJson(
                context.loadJSONFromAsset("dynamic"),
                Array<SimplePropertiesResponse>::class.java
            ).asList().toListSimpleProperties()
        )
    }
}
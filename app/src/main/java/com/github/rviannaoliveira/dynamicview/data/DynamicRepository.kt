package com.github.rviannaoliveira.dynamicview.data

import android.content.Context
import com.github.rviannaoliveira.dynamic.core.data.model.base.SimplePropertiesResponse
import com.github.rviannaoliveira.dynamic.core.data.toListSimpleProperties
import com.github.rviannaoliveira.dynamic.core.data.model.base.SimpleProperties
import com.github.rviannaoliveira.dynamic.core.extensions.loadJSONFromAsset
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

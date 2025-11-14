package org.example.project.data

import com.github.codandotv.craftd.androidcore.data.model.base.SimpleProperties
import craftd.app_sample_cmp.generated.resources.Res
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

interface SampleCraftDRepository {
    suspend fun getDynamic(): Flow<List<SimpleProperties>>
}

class SampleCraftDRepositoryImpl() : SampleCraftDRepository {

    override suspend fun getDynamic(): Flow<List<SimpleProperties>> = flow {
        val jsonString = Res.readBytes("files/dynamic.json")
            .decodeToString()

        val list = Json { ignoreUnknownKeys = true }
            .decodeFromString<List<SimpleProperties>>(jsonString)

        emit(list)
    }
}

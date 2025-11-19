package org.example.project.presentation.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import org.example.project.presentation.compose.customview.MySampleButtonComposeBuilder
import com.github.codandotv.craftd.compose.builder.CraftDBuilderManager
import com.github.codandotv.craftd.compose.ui.CraftDynamic
import org.example.project.data.SampleCraftDRepositoryImpl

@Composable
fun InitialScreen(
    vm: SampleCraftDComposeViewModel = SampleCraftDComposeViewModel(SampleCraftDRepositoryImpl())
) {
    val properties by vm.properties.collectAsState()
    val craftdBuilderManager = remember {
        CraftDBuilderManager().add(
            MySampleButtonComposeBuilder(),
        )
    }
    LaunchedEffect(Unit) {
        vm.loadProperties()
    }

    CraftDynamic(
        properties = properties,
        craftDBuilderManager = craftdBuilderManager
    ) {
        println(
            ">>>> category ${it.analytics?.category} -" + " action ${it.analytics?.action} -" + " label  ${it.analytics?.label} -" + " deeplink ${it.deeplink}"
        )
    }
}
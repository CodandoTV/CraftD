package com.github.codandotv.craftd.app_sample.presentation.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.github.codandotv.craftd.app_sample.presentation.compose.customview.MySampleButtonComposeBuilder
import com.github.codandotv.craftd.compose.builder.CraftDComposeBuilders
import com.github.codandotv.craftd.compose.ui.CraftDComposeController

@Composable
fun InitialScreen(
    vm: SampleCraftDComposeViewModel
) {
    val properties by vm.properties.collectAsStateWithLifecycle()
    val dynamicBuilder = remember {
        CraftDComposeBuilders().addBuilderRender(
            MySampleButtonComposeBuilder()
        )
    }
    LaunchedEffect(Unit) {
        vm.loadProperties()
    }

    CraftDComposeController(
        properties = properties,
        dynamicBuilder = dynamicBuilder
    ) {
        println(
            ">>>> category ${it.analytics?.category} -" + " action ${it.analytics?.action} -" + " label  ${it.analytics?.label} -" + " deeplink ${it.deeplink}"
        )
    }
}
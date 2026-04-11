package com.github.codandotv.craftd.app_sample.presentation.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.github.codandotv.craftd.app_sample.presentation.compose.customview.MySampleButtonComposeBuilder
import com.github.codandotv.craftd.compose.builder.CraftDBuilderManager
import com.github.codandotv.craftd.compose.ui.CraftDynamic
import com.github.codandotv.craftd.compose.ui.image.CraftDImageBuilder

@Composable
fun InitialScreen(
    vm: SampleCraftDComposeViewModel
) {
    val properties by vm.properties.collectAsStateWithLifecycle()
    val craftdBuilderManager = remember {
        CraftDBuilderManager().add(
            MySampleButtonComposeBuilder(),
            CraftDImageBuilder(
                imageLoader = { url, contentDescription, modifier ->
                    AsyncImage(
                        model = url,
                        contentDescription = contentDescription,
                        modifier = modifier,
                    )
                }
            ),
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
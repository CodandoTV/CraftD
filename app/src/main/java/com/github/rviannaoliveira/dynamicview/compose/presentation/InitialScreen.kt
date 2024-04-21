package com.github.rviannaoliveira.dynamicview.compose.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.github.rviannaoliveira.dynamic.compose.presentation.builder.DynamicComposeBuilders
import com.github.rviannaoliveira.dynamic.compose.presentation.ui.DynamicComposeController

@Composable
fun InitialScreen(
    vm: DynamicComposeViewModel
) {
    val properties by vm.properties.collectAsStateWithLifecycle()
    val dynamicBuilder = remember {
        DynamicComposeBuilders()
    }
    LaunchedEffect(Unit) {
        vm.loadProperties()
    }

    DynamicComposeController(
        properties = properties,
        dynamicBuilder = dynamicBuilder
    ) {
        println(
            ">>>> category ${it.analytics?.category} -" + " action ${it.analytics?.action} -" + " label  ${it.analytics?.label} -" + " deeplink ${it.deeplink}"
        )
    }
}
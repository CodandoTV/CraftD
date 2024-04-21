package com.github.rviannaoliveira.dynamic.compose.presentation.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.rviannaoliveira.dynamic.compose.presentation.builder.DynamicComposeBuilders
import com.github.rviannaoliveira.dynamic.core.data.model.action.DynamicActionProperties
import com.github.rviannaoliveira.dynamic.core.data.model.base.SimpleProperties

@Composable
fun DynamicComposeController(
    properties: List<SimpleProperties>,
    modifier: Modifier = Modifier,
    dynamicBuilder : DynamicComposeBuilders,
    onAction: (DynamicActionProperties) -> Unit
) {
    LazyColumn(
        modifier
    ) {
        items(
            count = properties.size,
          ) { index ->
            val model = properties[index]
            val builder = dynamicBuilder.getBuilder(model.key)
            builder.craft(model = model) {
                onAction(it)
            }
        }
    }
}
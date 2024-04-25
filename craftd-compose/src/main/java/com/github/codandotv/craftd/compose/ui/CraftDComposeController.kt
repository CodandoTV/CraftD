package com.github.codandotv.craftd.compose.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.codandotv.craftd.androidcore.data.model.action.ActionProperties
import com.github.codandotv.craftd.androidcore.data.model.base.SimpleProperties
import com.github.codandotv.craftd.compose.builder.CraftDComposeBuilders

@Composable
fun CraftDComposeController(
    properties: List<SimpleProperties>,
    modifier: Modifier = Modifier,
    craftBuilder : CraftDComposeBuilders,
    onAction: (ActionProperties) -> Unit
) {
    LazyColumn(
        modifier
    ) {
        items(
            count = properties.size,
          ) { index ->
            val model = properties[index]
            val builder = craftBuilder.getBuilder(model.key)
            builder.craft(model = model) {
                onAction(it)
            }
        }
    }
}
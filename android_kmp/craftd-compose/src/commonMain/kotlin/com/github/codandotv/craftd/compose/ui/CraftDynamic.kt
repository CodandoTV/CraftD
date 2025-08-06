package com.github.codandotv.craftd.compose.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.codandotv.craftd.androidcore.data.model.action.ActionProperties
import com.github.codandotv.craftd.androidcore.data.model.base.SimpleProperties
import com.github.codandotv.craftd.compose.builder.CraftDBuilderManager
import kotlinx.collections.immutable.ImmutableList

@Composable
fun CraftDynamic(
    properties: ImmutableList<SimpleProperties>,
    modifier: Modifier = Modifier,
    craftDBuilderManager : CraftDBuilderManager,
    onAction: (ActionProperties) -> Unit
) {
    LazyColumn(
        modifier
    ) {
        items(
            count = properties.size,
          ) { index ->
            val model = properties[index]
            val builder = craftDBuilderManager.getBuilder(model.key)
            builder.craft(model = model) {
                onAction(it)
            }
        }
    }
}
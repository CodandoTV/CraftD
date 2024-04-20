package com.github.rviannaoliveira.dynamicview.compose.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.rviannaoliveira.dynamic.core.data.model.base.SimpleProperties
import com.github.rviannaoliveira.dynamicview.data.DynamicRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DynamicComposeViewModel(private val repository: DynamicRepository) : ViewModel() {
    private val _properties = MutableStateFlow(emptyList<SimpleProperties>())
    val properties = _properties.stateIn(
        viewModelScope, SharingStarted.Eagerly,
        initialValue = _properties.value
    )
    fun loadProperties() {
        viewModelScope.launch {
            repository.getDynamic().collectLatest { newlist ->
                    _properties.update {
                        newlist
                    }
                }
        }
    }
}
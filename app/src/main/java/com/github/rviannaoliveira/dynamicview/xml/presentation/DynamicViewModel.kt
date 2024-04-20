package com.github.rviannaoliveira.dynamicview.xml.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.rviannaoliveira.dynamic.core.data.model.action.DynamicActionProperties
import com.github.rviannaoliveira.dynamic.core.data.model.base.SimpleProperties
import com.github.rviannaoliveira.dynamic.core.presentation.DynamicViewListener
import com.github.rviannaoliveira.dynamic.xml.presentation.builder.DynamicBuilders
import com.github.rviannaoliveira.dynamic.xml.presentation.ui.DynamicView
import com.github.rviannaoliveira.dynamicview.data.DynamicRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DynamicViewModel(
    val dynamic: DynamicView, val repository: DynamicRepository
) : ViewModel() {
    private val _analytics = MutableLiveData<String>()
    val analytics: LiveData<String>
        get() = _analytics

    private val _deeplink = MutableLiveData<String>()
    val deeplink: LiveData<String>
        get() = _deeplink

    fun loadDynamic() {
        viewModelScope.launch {
            repository.getDynamic().catch {
                    it.printStackTrace()
                }.collect {
                    setupDynamicRender(it)
                    dynamic.setViewObjectDiff(it)
                }
        }
    }

    private fun setupDynamicRender(list: List<SimpleProperties>) {
        dynamic.registerRenderers(
            DynamicBuilders().getBuilderRenders(
            simpleProperties = list,
        ) { action ->
            listener.invoke(action)
        })
    }

    private val listener = object : DynamicViewListener {
        override fun invoke(actionProperties: DynamicActionProperties) {
            actionProperties.analytics?.let {
                _analytics.value =
                    "categoria: ${it.category}, ação: ${it.action}, label: ${it.label}"
            }
            actionProperties.deeplink?.let {
                _deeplink.value = it
            }
        }
    }
}
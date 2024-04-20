package com.github.rviannaoliveira.dynamicview.xml.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.codandotv.craftd.androidcore.data.model.action.ActionProperties
import com.github.codandotv.craftd.androidcore.data.model.base.SimpleProperties
import com.github.codandotv.craftd.androidcore.presentation.CraftDViewListener
import com.github.rviannaoliveira.dynamic.xml.presentation.builder.CraftDBuilders
import com.github.rviannaoliveira.dynamic.xml.presentation.ui.CraftDView
import com.github.rviannaoliveira.dynamicview.data.DynamicRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DynamicViewModel(
    val dynamic: CraftDView, val repository: DynamicRepository
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
            CraftDBuilders().getBuilderRenders(
            simpleProperties = list,
        ) { action ->
            listener.invoke(action)
        })
    }

    private val listener = object : CraftDViewListener {
        override fun invoke(actionProperties: ActionProperties) {
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
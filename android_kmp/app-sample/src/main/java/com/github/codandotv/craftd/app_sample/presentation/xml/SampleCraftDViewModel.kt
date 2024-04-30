package com.github.codandotv.craftd.app_sample.presentation.xml

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.codandotv.craftd.androidcore.data.model.action.ActionProperties
import com.github.codandotv.craftd.androidcore.data.model.base.SimpleProperties
import com.github.codandotv.craftd.androidcore.presentation.CraftDViewListener
import com.github.codandotv.craftd.app_sample.data.SampleCraftDRepository
import com.github.codandotv.craftd.xml.builder.CraftDBuilderManager
import com.github.codandotv.craftd.xml.ui.CraftDView
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class SampleCraftDViewModel(
    val craft: CraftDView, val repository: SampleCraftDRepository
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
                craft.setViewObjectDiff(it)
            }
        }
    }

    private fun setupDynamicRender(list: List<SimpleProperties>) {
        craft.registerRenderers(
            CraftDBuilderManager.getBuilderRenders(
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
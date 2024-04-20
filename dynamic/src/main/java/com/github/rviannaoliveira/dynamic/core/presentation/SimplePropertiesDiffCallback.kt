package com.github.rviannaoliveira.dynamic.core.presentation

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.github.rviannaoliveira.dynamic.core.data.model.base.SimpleProperties
import java.util.*


object SimplePropertiesItemCallback : DiffUtil.ItemCallback<SimpleProperties>(){
    override fun areItemsTheSame(oldItem: SimpleProperties, newItem: SimpleProperties): Boolean {
        return try {
            oldItem.key == newItem.key
        } catch (e: Exception) {
            return false
        }
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: SimpleProperties, newItem: SimpleProperties): Boolean {

        val oldItemValue = oldItem.value
        val newItemValue = newItem.value

        return when {
            //Note: Because retrofit return AbstracMap
            oldItemValue is AbstractMap<*, *> && newItemValue is AbstractMap<*, *> -> oldItemValue == newItemValue
            else -> oldItemValue == newItemValue
        }
    }
}
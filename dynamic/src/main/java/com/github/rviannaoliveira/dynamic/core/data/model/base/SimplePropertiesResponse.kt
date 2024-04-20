package com.github.rviannaoliveira.dynamic.core.data.model.base

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class SimplePropertiesResponse(
    @JsonProperty("key") val key: String = "",
    @JsonProperty("value") val value: Any? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class DataSimplePropertiesResponse(
      val data : List<SimplePropertiesResponse>
)
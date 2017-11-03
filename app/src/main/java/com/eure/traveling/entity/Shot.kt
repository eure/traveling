package com.eure.traveling.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class Shot {

    var id: Int = 0
    var title: String? = null
    @JsonProperty("likes_count")
    var likesCount: Int = 0
    @JsonProperty("images")
    var image: Image? = null
    @JsonProperty("user")
    var designer: Designer? = null
}

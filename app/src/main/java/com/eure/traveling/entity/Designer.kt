package com.eure.traveling.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Designer {

    var id: Int = 0
    var name: String = ""
}

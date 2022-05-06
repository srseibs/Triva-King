package com.sailinghawklabs.triviaking.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val name: String,
    val id: Int,
)

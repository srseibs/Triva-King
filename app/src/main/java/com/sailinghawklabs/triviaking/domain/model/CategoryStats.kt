package com.sailinghawklabs.triviaking.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CategoryStats(
    val numTotal: Int,
    val numEasy: Int,
    val numMedium: Int,
    val numHard: Int,
)
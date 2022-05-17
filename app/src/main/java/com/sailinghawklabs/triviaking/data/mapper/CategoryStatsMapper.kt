package com.sailinghawklabs.triviaking.data.mapper


import com.sailinghawklabs.triviaking.data.remote.dto.CategoryStatsResponseDto
import com.sailinghawklabs.triviaking.domain.model.CategoryStats

fun CategoryStatsResponseDto.toCategoryStats(): CategoryStats {

    return CategoryStats(
        numEasy = statistics.numEasy,
        numMedium = statistics.numMedium,
        numHard = statistics.numHard,
        numTotal = statistics.totalQuestions,
    )

}
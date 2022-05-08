package com.sailinghawklabs.triviaking.domain.usecase

import com.sailinghawklabs.triviaking.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface GetCategories {
    suspend operator fun invoke(): Flow<Result<List<Category>>>
}
package com.sailinghawklabs.triviaking.data.mapper

import com.sailinghawklabs.triviaking.data.remote.dto.CategoriesResponseDto
import com.sailinghawklabs.triviaking.data.remote.dto.toCategory
import com.sailinghawklabs.triviaking.domain.model.Category


fun CategoriesResponseDto.toCategoryList(): List<Category> {

    return triviaCategories.map{ it.toCategory() }

}
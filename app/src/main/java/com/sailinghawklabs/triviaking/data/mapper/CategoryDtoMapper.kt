package com.sailinghawklabs.triviaking.data.mapper

import com.sailinghawklabs.triviaking.data.remote.dto.CategoriesResponseDto
import com.sailinghawklabs.triviaking.data.remote.dto.TriviaCategoryDto
import com.sailinghawklabs.triviaking.domain.model.Category


fun CategoriesResponseDto.toCategoryList(): List<Category> {

    return triviaCategories.map{ it.toCategory() }

}

fun TriviaCategoryDto.toCategory(): Category  = Category(id = id, name= name)

fun List<TriviaCategoryDto>.toCategoryList(): List<Category> = map{ it.toCategory() }
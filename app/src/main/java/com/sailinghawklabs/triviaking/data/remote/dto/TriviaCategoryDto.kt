package com.sailinghawklabs.triviaking.data.remote.dto

import com.sailinghawklabs.triviaking.domain.model.Category

data class TriviaCategoryDto(
    val id: Int,
    val name: String
)


fun TriviaCategoryDto.toCategory(): Category  = Category(id = id, name= name)

fun List<TriviaCategoryDto>.toCategoryList(): List<Category> = map{ it.toCategory() }

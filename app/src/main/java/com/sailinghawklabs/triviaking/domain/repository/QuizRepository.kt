package com.sailinghawklabs.triviaking.domain.repository

import com.sailinghawklabs.triviaking.util.Result
import com.sailinghawklabs.triviaking.domain.model.Category
import com.sailinghawklabs.triviaking.domain.model.Question
import kotlinx.coroutines.flow.Flow


interface QuizRepository {

    suspend fun fetchQuestionSet(
        numberOfQuestions: Int,
        difficultyString: String?,
        categoryId: Int?,
    ) : Flow<Result<List<Question>>>

    suspend fun fetchCategories() : Flow<Result<List<Category>>>
}
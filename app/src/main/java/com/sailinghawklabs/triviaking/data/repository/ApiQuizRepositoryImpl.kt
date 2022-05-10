package com.sailinghawklabs.triviaking.data.repository

import com.sailinghawklabs.triviaking.data.mapper.toCategoryList
import com.sailinghawklabs.triviaking.data.mapper.toQuestion
import com.sailinghawklabs.triviaking.data.remote.OpenTriviaDatabaseApi
import com.sailinghawklabs.triviaking.data.remote.dto.ResponseCode
import com.sailinghawklabs.triviaking.domain.model.Category
import com.sailinghawklabs.triviaking.domain.model.Question
import com.sailinghawklabs.triviaking.domain.repository.QuizRepository
import com.sailinghawklabs.triviaking.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class ApiQuizRepositoryImpl @Inject constructor(
    private val api: OpenTriviaDatabaseApi,
) : QuizRepository {

    override suspend fun fetchQuestionSet(
        numberOfQuestions: Int,
        difficultyString: String?,
        categoryId: Int?,
    ): Flow<Result<List<Question>>> {

        return flow {
            emit(Result.Loading(isLoading = true))

           try {
                val response = api.getQuestions(
                    quantity = numberOfQuestions,
                    categoryId = categoryId,
                    difficulty = difficultyString,
                )
                val responseCode = response.responseCode
                val responseData = response.results

                if (responseCode != ResponseCode.SUCCESS) {
                    emit(Result.Error(message = ResponseCode.toMessage(responseCode)))
                    return@flow
                }

                emit(Result.Loading(isLoading = false))

                emit(
                    Result.Success(
                        responseData.map { it.toQuestion() }
                    )
                )

            } catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error("Couldn't load data [IO]"))

            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error("Couldn't load data [HTTP]"))

            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error("Unknown error"))
            }
        }
    }


    override suspend fun fetchCategories(): Flow<Result<List<Category>>> {
        return flow {

            emit(Result.Loading(isLoading = true))

            try {
                val result = api.getCategories()
                emit(Result.Success(result.toCategoryList()))

            } catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error("Couldn't load data [IO]"))

            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error("Couldn't load data [HTTP]"))

            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error("Unknown error"))
                null
            }
        }
    }

}
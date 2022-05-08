package com.sailinghawklabs.triviaking.ui.screen.category

import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sailinghawklabs.triviaking.data.remote.OpenTriviaDatabaseApi
import com.sailinghawklabs.triviaking.data.remote.dto.toCategoryList
import com.sailinghawklabs.triviaking.domain.model.Category
import com.sailinghawklabs.triviaking.domain.model.GamePreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CategorySelectViewModel @Inject constructor(

    // need to use repository
    private val api: OpenTriviaDatabaseApi,
    private val dataStore: DataStore<GamePreferences>
) : ViewModel() {

    private val _categoryState =
        MutableStateFlow<CategorySelectState>(CategorySelectState.StartState)
    val categoryState = _categoryState.asStateFlow()

    init {
        fetchCategories()
    }

    fun updateCategory(newCategory: Category?) {
        viewModelScope.launch {
            storeCategory(newCategory)
        }
    }

    private suspend fun storeCategory(newCategory: Category?) {
        dataStore.updateData {
            it.copy(
                category = newCategory
            )
        }
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            try {
                _categoryState.tryEmit(CategorySelectState.LoadingState)
                val categoryResponse = api.getCategories()
                val categoryList = categoryResponse.triviaCategories.map { it }.sortedBy { it.name }
                _categoryState.tryEmit(CategorySelectState.ListAvailable(categoryList.toCategoryList()))

            } catch (e: Exception) {
                _categoryState.emit(
                    CategorySelectState.Error(e.localizedMessage ?: "Unknown")
                )
            }
        }
    }

}
package com.bignerdranch.android.bored.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import data.repository.BoredIdeaRepositoryImpl
import domain.usecase.GetBoredIdeaUsecase

class MainViewModelProviderFactory(
val getBoredIdeaUsecase: GetBoredIdeaUsecase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(getBoredIdeaUsecase) as T
    }
}
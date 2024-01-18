package com.bignerdranch.android.bored.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.repository.BoredIdeaRepositoryImpl
import domain.model.BoredIdea
import domain.usecase.GetBoredIdeaUsecase
import kotlinx.coroutines.launch
import retrofit2.Response
import util.Resource

class MainViewModel (val getBoredIdeaUsecase: GetBoredIdeaUsecase) : ViewModel() {
    val boredIdea: MutableLiveData<Resource<BoredIdea>> = MutableLiveData()
    var boredIdeaResponse: BoredIdea? = null

    fun getBoredIdea() = viewModelScope.launch {
        boredIdea.postValue(Resource.Loading())
        val response = getBoredIdeaUsecase.execute()
        boredIdea.postValue(handleBoredIdeaResponse(response))
    }

    private fun handleBoredIdeaResponse(response: Response<BoredIdea>): Resource<BoredIdea> {
        if(response.isSuccessful){
            response.body()?.let {
                resultResponse ->
                boredIdeaResponse = resultResponse
                return Resource.Success(boredIdeaResponse?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}
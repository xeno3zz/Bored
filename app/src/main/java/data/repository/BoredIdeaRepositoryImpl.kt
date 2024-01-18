package data.repository

import api.BoredIdeaAPI
import api.RetrofitInstance
import domain.model.BoredIdea
import domain.repository.BoredIdeaRepository
import retrofit2.Response

class BoredIdeaRepositoryImpl : BoredIdeaRepository {
    override suspend fun getBoredIdea(): Response<BoredIdea> {
        return RetrofitInstance.api.getBoredIdea()
    }
}
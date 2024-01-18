package domain.repository

import domain.model.BoredIdea
import retrofit2.Response

interface BoredIdeaRepository {
    suspend fun getBoredIdea(): Response<BoredIdea>
}
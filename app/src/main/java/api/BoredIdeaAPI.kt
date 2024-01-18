package api

import domain.model.BoredIdea
import retrofit2.Response
import retrofit2.http.GET

interface BoredIdeaAPI {
    @GET("api/activity")
    suspend fun getBoredIdea():Response<BoredIdea>
}
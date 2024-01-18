package domain.usecase

import data.repository.BoredIdeaRepositoryImpl
import domain.model.BoredIdea
import domain.repository.BoredIdeaRepository
import retrofit2.Response

class GetBoredIdeaUsecase(private val boredIdeaRepository: BoredIdeaRepository) {

     suspend fun execute(): Response<BoredIdea>{
        return boredIdeaRepository.getBoredIdea()
    }
}
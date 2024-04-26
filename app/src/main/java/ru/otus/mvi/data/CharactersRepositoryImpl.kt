package ru.otus.mvi.data

import io.reactivex.Single
import kotlinx.coroutines.delay
import ru.otus.mvi.domain.RaMCharacter
import ru.otus.mvi.domain.CharactersRepository
import java.util.Random

class CharactersRepositoryImpl(
    private val api: RAMRetrofitService
) : CharactersRepository {

    private var count: Int = 0

    override suspend fun getAllCharacters(): Result<List<RaMCharacter>> = runCatching {
        count++
        if (count % 2 == 1) {
            api.getCharacters(page = Random().nextInt(20)).results
                .map { dto ->
                    RaMCharacter(
                        id = dto.id,
                        name = dto.name,
                        image = dto.image,
                    )
                }
        } else {
            delay(1000)
            throw IllegalStateException("Server is overloaded")
        }
    }

    override fun getAllCharactersSingle(): Single<List<RaMCharacter>> {
        count++
        return if (count % 2 == 1) {
            api.getCharactersSingle(page = Random().nextInt(20))
                .map { response ->
                    response.results.map { dto ->
                        RaMCharacter(
                            id = dto.id,
                            name = dto.name,
                            image = dto.image,
                        )
                    }
                }
        } else {
            Single.error(IllegalStateException("Server is overloaded"))
        }
    }
}

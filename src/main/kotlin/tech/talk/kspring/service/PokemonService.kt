package tech.talk.kspring.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import tech.talk.kspring.model.Pokemon
import tech.talk.kspring.repository.PokemonRepository

@Service
class PokemonService {

    @Autowired
    private lateinit var repository: PokemonRepository

    fun doSearch(name: String): Pokemon {
        return repository.doSearch(name) ?: Pokemon()
    }
}
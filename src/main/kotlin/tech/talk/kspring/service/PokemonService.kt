package tech.talk.kspring.service

import tech.talk.kspring.model.Pokemon
import tech.talk.kspring.resource.PokemonResource

class PokemonService(private val repository: PokemonResource) {

    fun doSearch(name: String): Pokemon {
        return repository.doSearch(name) ?: Pokemon()
    }
}
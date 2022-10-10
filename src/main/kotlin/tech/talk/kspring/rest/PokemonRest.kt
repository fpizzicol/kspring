package tech.talk.kspring.rest

import org.slf4j.LoggerFactory
import tech.talk.kspring.model.Pokemon
import tech.talk.kspring.service.PokemonService


class PokemonRest(private val service: PokemonService) {

        private val logger = LoggerFactory.getLogger(this.javaClass)

    fun getPokemon(name: String): Pokemon {
        logger.info("Starting Poke Search: $name")
        val pokemon = service.doSearch(name)
        logger.info("Poke Search Finished: $name")
        return pokemon
    }
}


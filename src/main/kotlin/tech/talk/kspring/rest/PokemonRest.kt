package tech.talk.kspring.rest

import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import tech.talk.kspring.model.Pokemon
import tech.talk.kspring.service.PokemonService


@RestController
@RequestMapping("/pokemon", produces = [MediaType.APPLICATION_JSON_VALUE])
class PokemonRest {

    @Autowired
    private lateinit var logger: Logger

    @Autowired
    private lateinit var service: PokemonService

    @GetMapping("/{name}")
    fun getByName(@PathVariable("name") name: String): Pokemon {
        logger.info("Starting Poke Search: $name")

        val pokemon = service.doSearch(name)

        logger.info("Poke Search Finished: $name")
        return pokemon
    }
}

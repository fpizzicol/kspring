package tech.talk.kspring.repository

import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder
import tech.talk.kspring.model.Pokemon
import java.time.Duration

@Component
class PokemonRepository {

    @Autowired
    private lateinit var logger: Logger

    private val baseUrl = "https://pokeapi.co/api/v2/pokemon/"

    fun doSearch(name: String) : Pokemon? = try {
        val restTemplate = RestTemplateBuilder()
            .setConnectTimeout(Duration.ofSeconds(10))
            .build()

        val toUri = UriComponentsBuilder
            .fromHttpUrl(baseUrl)
            .pathSegment(name)
            .build()
            .toUri()

        restTemplate.getForObject(toUri, Pokemon::class.java)
    } catch (e: Exception) {
        logger.error("Sad error...", e)
        null
    }
}
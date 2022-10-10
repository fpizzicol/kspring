package tech.talk.kspring.resource

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import org.slf4j.LoggerFactory
import tech.talk.kspring.model.Pokemon

interface PokemonResource {
    fun doSearch(name: String): Pokemon?
}

class PokemonResourceImpl : PokemonResource {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    private val client = OkHttpClient()
    private val baseUrl = "https://pokeapi.co/api/v2/pokemon/"

    private val json = Json { ignoreUnknownKeys = true }

    override fun doSearch(name: String) : Pokemon? = try {
        val request = Request.Builder().url(baseUrl+name).get().build()
        val response = client.newCall(request).execute()

        val pokemon = response.body?.string()?.let { json.decodeFromString<Pokemon>(it) }
        logger.info("Success ${pokemon?.id} - ${pokemon?.name}")
        pokemon
    } catch (e: Exception) {
        logger.error( "Sad error...", e)
        null
    }
}
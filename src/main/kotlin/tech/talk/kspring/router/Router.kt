package tech.talk.kspring.router

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import tech.talk.kspring.rest.DefaultRest
import tech.talk.kspring.rest.PokemonRest

fun Application.configureRouting() {
    val defaultRest by inject<DefaultRest>()
    val pokemonRest by inject<PokemonRest>()
    routing {

        route("/") {
            get("/") {
                call.respond(defaultRest.getData())
            }
        }
        route("/pokemon") {
            get("{name?}") {
                val pokemon = pokemonRest.getPokemon(call.parameters["name"] ?: "-1")
                call.respond(HttpStatusCode.OK, pokemon)
            }
        }
    }
}
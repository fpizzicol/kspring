package tech.talk.kspring.di

import org.koin.dsl.module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import tech.talk.kspring.resource.PokemonResource
import tech.talk.kspring.resource.PokemonResourceImpl
import tech.talk.kspring.service.PokemonService
import tech.talk.kspring.rest.PokemonRest
import tech.talk.kspring.rest.DefaultRest


val appModule = module {
    singleOf(::PokemonResourceImpl) { bind<PokemonResource>() }
    singleOf(::PokemonService)
    singleOf(::PokemonRest)
    singleOf(::DefaultRest)
}
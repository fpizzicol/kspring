@file:Suppress("ArrayInDataClass")

package tech.talk.kspring.model

import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val id: Int = -1,
    val name: String = "missingno",
    val sprites: Sprites? = null,
    val abilities: Array<PokemonAbility>? = null,
    val baseExperience: Int = -1,
    val forms: Array<PokemonForm>? = null,
    val height: Int = -1,
    val isDefault: Boolean = false,
    val moves: Array<PokemonMove>? = null,
    val order: Int = -1,
    val stats: Array<PokemonStat>? = null,
    val types: Array<PokemonType>? = null,
    val weight: Int = -1,
)

@Serializable
data class PokemonBaseData(
    val name: String = "",
    val url: String = "",
)


@Serializable
data class PokemonMove(
    val move: PokemonBaseData? = null,
)

@Serializable
data class PokemonType(
    val slot: Int = -1,
    val type: PokemonBaseData? = null,
)

@Serializable
data class PokemonStat(
    val baseStat: Int = -1,
    val effort: Int = -1,
    val stat: PokemonBaseData? = null,
)

@Serializable
data class PokemonAbility(
    val ability: PokemonBaseData? = null,
    val isHidden: Boolean = false,
    val slot: Int = -1,
)

@Serializable
data class PokemonForm(
    val name: String? = null,
    val url: String? = null,
)

@Serializable
data class Sprites(
    val back_default: String? = null,
    val front_default: String? = null,
)

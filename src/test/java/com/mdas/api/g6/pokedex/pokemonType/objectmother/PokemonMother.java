package com.mdas.api.g6.pokedex.pokemonType.objectmother;

import com.mdas.api.g6.pokedex.pokemonType.domain.Pokemon;

public class PokemonMother {
    public static Pokemon random() {
        return new Pokemon(
                PokemonIdMother.random(),
                PokemonNameMother.random(),
                PokemonTypesMother.random()
        );
    }
}

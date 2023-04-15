package com.mdas.api.g6.pokedex.pokemon.objectmother;

import com.mdas.api.g6.pokedex.pokemon.domain.Pokemon;

public class PokemonMother {
    public static Pokemon random() {
        return new Pokemon(
                PokemonIdMother.random(),
                PokemonNameMother.random(),
                PokemonHeightMother.random(),
                PokemonWeightMother.random(),
                PokemonFavoritesCounterMother.random()
        );
    }
}

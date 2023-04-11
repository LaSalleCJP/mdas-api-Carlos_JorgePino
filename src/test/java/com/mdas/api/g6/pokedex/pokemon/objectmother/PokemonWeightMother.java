package com.mdas.api.g6.pokedex.pokemon.objectmother;

import com.github.javafaker.Faker;
import com.mdas.api.g6.pokedex.pokemon.domain.valueobject.PokemonWeight;

public class PokemonWeightMother {
    public static PokemonWeight random() {
        return new PokemonWeight(Faker.instance().number().randomDouble(2, 0, 1000));
    }

    public static PokemonWeight random(double pokemonWeight) {
        return new PokemonWeight(pokemonWeight);
    }
}
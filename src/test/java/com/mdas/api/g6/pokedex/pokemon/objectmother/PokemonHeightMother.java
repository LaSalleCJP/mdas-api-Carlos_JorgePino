package com.mdas.api.g6.pokedex.pokemon.objectmother;

import com.github.javafaker.Faker;
import com.mdas.api.g6.pokedex.pokemon.domain.valueobject.PokemonHeight;

public class PokemonHeightMother {
    public static PokemonHeight random() {
        return new PokemonHeight(
                Faker.instance().number().randomDouble(2, 0, 100));
    }

    public static PokemonHeight random(double pokemonHeight) {
        return new PokemonHeight(pokemonHeight);
    }
}

package com.mdas.api.g6.pokedex.pokemonType.objectmother;

import com.mdas.api.g6.pokedex.pokemonType.domain.valueobject.PokemonType;
import com.mdas.api.g6.pokedex.pokemonType.domain.valueobject.PokemonTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PokemonTypesMother {
    public static PokemonTypes random() {
        List<PokemonType> pokemonTypes = new ArrayList<>();
        Random rand = new Random();
        int iterations = rand.nextInt(6) + 3; // generates a random number between 3 and 8 (inclusive)
        for (int i = 0; i < iterations; i++) {
            pokemonTypes.add(PokemonTypeMother.random());
        }
        return new PokemonTypes(pokemonTypes);
    }

    public static PokemonTypes random(List<PokemonType> pokemonTypes) {
        return new PokemonTypes(pokemonTypes);
    }
}

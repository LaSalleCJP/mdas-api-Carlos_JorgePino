package com.mdas.api.g6.pokedex.pokemon.objectmother;

import com.github.javafaker.Faker;
import com.mdas.api.g6.pokedex.pokemon.domain.valueobject.PokemonFavoritesCounter;
import com.mdas.api.g6.pokedex.pokemon.domain.valueobject.PokemonName;

public class PokemonFavoritesCounterMother {

    public static PokemonFavoritesCounter random() {
        return  new PokemonFavoritesCounter(Faker.instance().number().randomDigit());
    }

    public static PokemonFavoritesCounter random(int counter) {
        return  new PokemonFavoritesCounter(counter);
    }
}

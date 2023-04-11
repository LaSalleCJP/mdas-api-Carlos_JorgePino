package com.mdas.api.g6.pokedex.pokemon.domain.exception;

public class PokemonNotFoundException extends Exception {
    public PokemonNotFoundException() {
        super("The pokemon with the provided id was not found.");
    }
}

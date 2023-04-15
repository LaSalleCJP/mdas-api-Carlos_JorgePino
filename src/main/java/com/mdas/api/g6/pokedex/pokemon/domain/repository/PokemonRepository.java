package com.mdas.api.g6.pokedex.pokemon.domain.repository;

import com.mdas.api.g6.pokedex.pokemon.domain.Pokemon;
import com.mdas.api.g6.pokedex.pokemon.domain.exception.PokemonNotFoundException;
import com.mdas.api.g6.pokedex.pokemon.domain.exception.RepositoryUnavailableException;
import com.mdas.api.g6.pokedex.pokemon.domain.valueobject.PokemonId;

public interface PokemonRepository {
    Pokemon getPokemonById(PokemonId pokemonId) throws PokemonNotFoundException, RepositoryUnavailableException;

    void Save(Pokemon pokemon);
}

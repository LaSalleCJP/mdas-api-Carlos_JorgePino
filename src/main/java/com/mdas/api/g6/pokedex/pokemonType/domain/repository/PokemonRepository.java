package com.mdas.api.g6.pokedex.pokemonType.domain.repository;

import com.mdas.api.g6.pokedex.pokemonType.domain.exception.RepositoryUnavailableException;
import com.mdas.api.g6.pokedex.pokemonType.domain.valueobject.PokemonName;
import com.mdas.api.g6.pokedex.pokemonType.domain.Pokemon;
import com.mdas.api.g6.pokedex.pokemonType.domain.exception.PokemonNotFoundException;

public interface PokemonRepository {
    Pokemon getPokemonByName(PokemonName name) throws PokemonNotFoundException, RepositoryUnavailableException;
}

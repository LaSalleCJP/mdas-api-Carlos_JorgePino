package com.mdas.api.g6.pokedex.pokemon.infrastructure.pokeapi.adapter;

import com.mdas.api.g6.pokedex.pokemon.domain.Pokemon;
import com.mdas.api.g6.pokedex.pokemon.domain.exception.PokemonNotFoundException;
import com.mdas.api.g6.pokedex.pokemon.domain.exception.RepositoryUnavailableException;
import com.mdas.api.g6.pokedex.pokemon.domain.repository.PokemonRepository;
import com.mdas.api.g6.pokedex.pokemon.domain.valueobject.PokemonFavoritesCounter;
import com.mdas.api.g6.pokedex.pokemon.domain.valueobject.PokemonId;
import com.mdas.api.g6.pokedex.pokemon.infrastructure.pokeapi.entity.PokemonApiEntity;
import com.mdas.api.g6.pokedex.pokemon.infrastructure.pokeapi.mapper.PokemonMapper;
import com.mdas.api.g6.pokedex.pokemon.infrastructure.pokeapi.repository.PokeHttpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class PokemonRepositoryAdapter implements PokemonRepository {

    private final PokeHttpRepository pokeApiHttpRepository;
    private final PokemonMapper pokemonMapper;
    Map<Integer, Integer> favoritesCounter = new HashMap<>();

    @Override
    public Pokemon getPokemonById(PokemonId pokemonId) throws PokemonNotFoundException, RepositoryUnavailableException {
        PokemonApiEntity pokeApiPokemonEntity = pokeApiHttpRepository.getPokemonById(pokemonId);
        Pokemon poke = pokemonMapper.toDomain(pokeApiPokemonEntity);
        poke.setPokemonFavoritesCounter(new PokemonFavoritesCounter(favoritesCounter.get(poke.getId().getId())));
        return poke;
    }

    @Override
    public void Save(Pokemon pokemon) {
        favoritesCounter.put(pokemon.getId().getId(),pokemon.getPokemonFavoritesCounter().getValue());
    }
}

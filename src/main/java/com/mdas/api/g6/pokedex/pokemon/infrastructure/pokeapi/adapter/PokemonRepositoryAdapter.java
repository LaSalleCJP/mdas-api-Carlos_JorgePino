package com.mdas.api.g6.pokedex.pokemon.infrastructure.pokeapi.adapter;

import com.mdas.api.g6.pokedex.pokemon.domain.Pokemon;
import com.mdas.api.g6.pokedex.pokemon.domain.exception.PokemonNotFoundException;
import com.mdas.api.g6.pokedex.pokemon.domain.exception.RepositoryUnavailableException;
import com.mdas.api.g6.pokedex.pokemon.domain.repository.PokemonRepository;
import com.mdas.api.g6.pokedex.pokemon.domain.valueobject.PokemonId;
import com.mdas.api.g6.pokedex.pokemon.infrastructure.pokeapi.entity.PokemonApiEntity;
import com.mdas.api.g6.pokedex.pokemon.infrastructure.pokeapi.mapper.PokemonMapper;
import com.mdas.api.g6.pokedex.pokemon.infrastructure.pokeapi.repository.PokeHttpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PokemonRepositoryAdapter implements PokemonRepository {

    private final PokeHttpRepository pokeApiHttpRepository;
    private final PokemonMapper pokemonMapper;

    @Override
    public Pokemon getPokemonById(PokemonId pokemonId) throws PokemonNotFoundException, RepositoryUnavailableException {
        PokemonApiEntity pokeApiPokemonEntity = pokeApiHttpRepository.getPokemonById(pokemonId);
        return pokemonMapper.toDomain(pokeApiPokemonEntity);
    }
}

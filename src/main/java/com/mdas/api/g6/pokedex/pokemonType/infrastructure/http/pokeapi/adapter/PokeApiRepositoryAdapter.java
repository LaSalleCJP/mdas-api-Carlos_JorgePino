package com.mdas.api.g6.pokedex.pokemonType.infrastructure.http.pokeapi.adapter;

import com.mdas.api.g6.pokedex.pokemonType.domain.exception.RepositoryUnavailableException;
import com.mdas.api.g6.pokedex.pokemonType.domain.valueobject.PokemonName;
import com.mdas.api.g6.pokedex.pokemonType.infrastructure.http.pokeapi.entity.PokemonApiEntity;
import com.mdas.api.g6.pokedex.pokemonType.domain.Pokemon;
import com.mdas.api.g6.pokedex.pokemonType.domain.exception.PokemonNotFoundException;
import com.mdas.api.g6.pokedex.pokemonType.domain.repository.PokemonRepository;
import com.mdas.api.g6.pokedex.pokemonType.infrastructure.http.pokeapi.repository.PokemonHttpRepository;
import com.mdas.api.g6.pokedex.pokemonType.infrastructure.http.pokeapi.mapper.PokemonApiMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PokeApiRepositoryAdapter implements PokemonRepository {

    private final PokemonHttpRepository pokeApiHttpRepository;
    private final PokemonApiMapper pokemonApiMapper;

    @Override
    public Pokemon getPokemonByName(PokemonName name) throws PokemonNotFoundException, RepositoryUnavailableException {
        PokemonApiEntity pokeApiPokemonEntity = pokeApiHttpRepository.getPokemonByName(name);
        return pokemonApiMapper.toDomain(pokeApiPokemonEntity);
    }
}

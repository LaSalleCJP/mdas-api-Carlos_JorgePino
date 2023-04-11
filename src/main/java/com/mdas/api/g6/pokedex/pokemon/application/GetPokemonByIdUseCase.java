package com.mdas.api.g6.pokedex.pokemon.application;

import com.mdas.api.g6.pokedex.pokemon.domain.Pokemon;
import com.mdas.api.g6.pokedex.pokemon.domain.exception.PokemonNotFoundException;
import com.mdas.api.g6.pokedex.pokemon.domain.exception.RepositoryUnavailableException;
import com.mdas.api.g6.pokedex.pokemon.domain.services.PokemonFinder;
import com.mdas.api.g6.pokedex.pokemon.domain.valueobject.PokemonId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetPokemonByIdUseCase {

    private final PokemonFinder pokemonDomainService;

    public Pokemon execute(Integer id)
            throws PokemonNotFoundException, RepositoryUnavailableException {
        PokemonId pokemonid = new PokemonId(id);
        return pokemonDomainService.getPokemonById(pokemonid);
    }
}

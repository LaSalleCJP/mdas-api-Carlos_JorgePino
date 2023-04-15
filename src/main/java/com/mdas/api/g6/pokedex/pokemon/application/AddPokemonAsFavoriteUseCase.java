package com.mdas.api.g6.pokedex.pokemon.application;

import com.mdas.api.g6.pokedex.pokemon.domain.Pokemon;
import com.mdas.api.g6.pokedex.pokemon.domain.exception.PokemonNotFoundException;
import com.mdas.api.g6.pokedex.pokemon.domain.exception.RepositoryUnavailableException;
import com.mdas.api.g6.pokedex.pokemon.domain.services.PokemonFinder;
import com.mdas.api.g6.pokedex.pokemon.domain.services.PokemonSaver;
import com.mdas.api.g6.pokedex.pokemon.domain.valueobject.PokemonId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddPokemonAsFavoriteUseCase {

    private final PokemonFinder pokemonFinder;

    private final PokemonSaver pokemonSaver;

    public void Execute(Integer id) throws PokemonNotFoundException, RepositoryUnavailableException {
        PokemonId pokemonid = new PokemonId(id);
        Pokemon pokemon =  pokemonFinder.getPokemonById(pokemonid);

        pokemon.increaseFavoritePokemonCounter();
        pokemonSaver.execute(pokemon);
    }
}

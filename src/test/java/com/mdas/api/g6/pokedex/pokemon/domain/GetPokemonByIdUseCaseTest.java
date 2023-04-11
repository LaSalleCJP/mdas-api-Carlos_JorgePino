package com.mdas.api.g6.pokedex.pokemon.domain;

import com.mdas.api.g6.pokedex.pokemon.application.GetPokemonByIdUseCase;
import com.mdas.api.g6.pokedex.pokemon.domain.Pokemon;
import com.mdas.api.g6.pokedex.pokemon.domain.exception.PokemonNotFoundException;
import com.mdas.api.g6.pokedex.pokemon.domain.exception.RepositoryUnavailableException;
import com.mdas.api.g6.pokedex.pokemon.domain.services.PokemonFinder;
import com.mdas.api.g6.pokedex.pokemon.objectmother.PokemonMother;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class GetPokemonByIdUseCaseTest {
    @Mock
    private PokemonFinder pokemonSearcher;

    @InjectMocks
    private GetPokemonByIdUseCase getPokemonByIdUseCase;

    @Test
    void execute_withValidId_returnsPokemon() throws PokemonNotFoundException, RepositoryUnavailableException {
        // Given
        Integer pokemonId = 1;
        Pokemon expectedPokemon = PokemonMother.random();
        when(pokemonSearcher.getPokemonById(Mockito.any())).thenReturn(expectedPokemon);

        // When
        Pokemon actualPokemon = getPokemonByIdUseCase.execute(pokemonId);

        // Then
        assertSame(expectedPokemon, actualPokemon);
    }

    @Test
    void execute_withInvalidId_throwsPokemonNotFoundException() throws PokemonNotFoundException, RepositoryUnavailableException {
        // Given
        Integer pokemonId = 999999;
        when(pokemonSearcher.getPokemonById(Mockito.any()))
                .thenThrow(new PokemonNotFoundException());

        // When and Then
        assertThrows(PokemonNotFoundException.class, () -> getPokemonByIdUseCase.execute(pokemonId));
    }

    @Test
    void execute_withServiceUnavailable_throwsRepositoryUnavailableException() throws PokemonNotFoundException, RepositoryUnavailableException {
        // Given
        Integer pokemonId = 1;
        when(pokemonSearcher.getPokemonById(Mockito.any()))
                .thenThrow(new RepositoryUnavailableException());

        // When and Then
        assertThrows(RepositoryUnavailableException.class, () -> getPokemonByIdUseCase.execute(pokemonId));
    }
}

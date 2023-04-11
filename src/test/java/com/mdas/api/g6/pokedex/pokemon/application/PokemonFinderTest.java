package com.mdas.api.g6.pokedex.pokemon.application;

import com.mdas.api.g6.pokedex.pokemon.domain.Pokemon;
import com.mdas.api.g6.pokedex.pokemon.domain.exception.PokemonNotFoundException;
import com.mdas.api.g6.pokedex.pokemon.domain.exception.RepositoryUnavailableException;
import com.mdas.api.g6.pokedex.pokemon.domain.repository.PokemonRepository;
import com.mdas.api.g6.pokedex.pokemon.domain.services.PokemonFinder;
import com.mdas.api.g6.pokedex.pokemon.domain.valueobject.PokemonId;
import com.mdas.api.g6.pokedex.pokemon.objectmother.PokemonIdMother;
import com.mdas.api.g6.pokedex.pokemon.objectmother.PokemonMother;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@ExtendWith(MockitoExtension.class)
public class PokemonFinderTest {
    @Mock
    private PokemonRepository pokemonRepository;

    @InjectMocks
    private PokemonFinder pokemonFinder;

    @Test
    void getPokemonById_withValidId_returnsPokemon() throws PokemonNotFoundException, RepositoryUnavailableException {
        // Given
        PokemonId pokemonId = PokemonIdMother.random();
        Pokemon expectedPokemon = PokemonMother.random();
        when(pokemonRepository.getPokemonById(Mockito.any())).thenReturn(expectedPokemon);

        // When
        Pokemon actualPokemon = pokemonFinder.getPokemonById(pokemonId);

        // Then
        assertEquals(expectedPokemon, actualPokemon);
    }

    @Test
    void getPokemonById_withInvalidId_throwsPokemonNotFoundException() throws PokemonNotFoundException, RepositoryUnavailableException {
        // Given
        PokemonId pokemonId = PokemonIdMother.random();
        when(pokemonRepository.getPokemonById(Mockito.any()))
                .thenThrow(new PokemonNotFoundException());

        // When and Then
        assertThrows(PokemonNotFoundException.class, () -> pokemonFinder.getPokemonById(pokemonId));
    }

    @Test
    void getPokemonById_withServiceUnavailable_throwsRepositoryUnavailableException() throws PokemonNotFoundException, RepositoryUnavailableException {
        // Given
        PokemonId pokemonId = PokemonIdMother.random();
        when(pokemonRepository.getPokemonById(Mockito.any()))
                .thenThrow(new RepositoryUnavailableException());

        // When and Then
        assertThrows(RepositoryUnavailableException.class, () -> pokemonFinder.getPokemonById(pokemonId));
    }
}

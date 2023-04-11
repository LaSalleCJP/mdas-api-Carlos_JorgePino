package com.mdas.api.g6.pokedex.pokemonType.application;

import com.mdas.api.g6.pokedex.pokemonType.domain.Pokemon;
import com.mdas.api.g6.pokedex.pokemonType.domain.exception.PokemonNotFoundException;
import com.mdas.api.g6.pokedex.pokemonType.domain.exception.RepositoryUnavailableException;
import com.mdas.api.g6.pokedex.pokemonType.domain.services.PokemonSearcher;
import com.mdas.api.g6.pokedex.pokemonType.domain.valueobject.PokemonName;
import com.mdas.api.g6.pokedex.pokemonType.objectmother.PokemonMother;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetPokemonByNameUseCaseTest {

    @Mock
    private PokemonSearcher pokemonSearcher;

    @InjectMocks
    private GetPokemonByNameUseCase getPokemonByNameUseCase;

    @Test
    public void shouldGetPokemonByName() throws PokemonNotFoundException, RepositoryUnavailableException {
        // Given
        Pokemon pokemon = PokemonMother.random();
        when(pokemonSearcher.getPokemonByName(Mockito.any(PokemonName.class)))
                .thenReturn(pokemon);
        // When
        Pokemon result = getPokemonByNameUseCase.execute(pokemon.getName().getName());
        // Then
        assertNotNull(result);
        assertEquals(pokemon.getId(), result.getId());
    }
}


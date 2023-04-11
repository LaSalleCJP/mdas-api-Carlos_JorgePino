package com.mdas.api.g6.pokedex.pokemon.acceptance;

import com.mdas.api.g6.pokedex.pokemon.domain.Pokemon;
import com.mdas.api.g6.shared.infrastructure.controller.dto.ApiResponse;
import org.apache.commons.lang3.reflect.TypeUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PokemonControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getPokemonById_ReturnsPokemon() {
        // Given
        int pokemonId = 25;

        // When
        ResponseEntity<ApiResponse<Pokemon>> response = restTemplate.exchange("/pokemon/detail?id=" + pokemonId,
                HttpMethod.GET, null, ParameterizedTypeReference.forType(TypeUtils.parameterize(ApiResponse.class, Pokemon.class)));

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody().getData());
        assertEquals(pokemonId, response.getBody().getData().getId().getId());
    }

    @Test
    public void getPokemonById_ReturnsNotFound_WhenPokemonNotFound() {
        // Given
        int pokemonId = 1000000;

        // When
        ResponseEntity<ApiResponse<Pokemon>> response = restTemplate.exchange("/pokemon/detail?id=" + pokemonId,
                HttpMethod.GET, null, ParameterizedTypeReference.forType(TypeUtils.parameterize(ApiResponse.class, Pokemon.class)));

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody().getData());
        assertEquals("The pokemon with the provided id was not found.", response.getBody().getMessage());
    }
}

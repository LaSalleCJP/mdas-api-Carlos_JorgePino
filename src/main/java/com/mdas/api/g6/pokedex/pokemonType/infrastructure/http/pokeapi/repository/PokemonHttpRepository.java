package com.mdas.api.g6.pokedex.pokemonType.infrastructure.http.pokeapi.repository;

import com.mdas.api.g6.pokedex.pokemonType.domain.exception.PokemonNotFoundException;
import com.mdas.api.g6.pokedex.pokemonType.domain.exception.RepositoryUnavailableException;
import com.mdas.api.g6.pokedex.pokemonType.domain.valueobject.PokemonName;
import com.mdas.api.g6.pokedex.pokemonType.infrastructure.http.pokeapi.entity.PokemonApiEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.*;

@Component
@RequiredArgsConstructor
public class PokemonHttpRepository {

    private RestTemplate restTemplate = new RestTemplate();
    @Value("${pokeapi.url}")
    private String baseUrl;

    public PokemonApiEntity getPokemonByName(PokemonName pokemonName) throws PokemonNotFoundException, RepositoryUnavailableException {
        String url = baseUrl + "/pokemon/" + pokemonName.getName();
        try {
            ResponseEntity<PokemonApiEntity> response = restTemplate.getForEntity(url, PokemonApiEntity.class);
            return response.getBody();
        } catch (HttpClientErrorException.NotFound e) {
            throw new PokemonNotFoundException();
        } catch (RestClientException e) {
            throw new RepositoryUnavailableException();
        }
    }
}

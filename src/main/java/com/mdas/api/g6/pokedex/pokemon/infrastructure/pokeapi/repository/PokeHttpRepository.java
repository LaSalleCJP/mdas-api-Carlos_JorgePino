package com.mdas.api.g6.pokedex.pokemon.infrastructure.pokeapi.repository;

import com.mdas.api.g6.pokedex.pokemon.domain.exception.PokemonNotFoundException;
import com.mdas.api.g6.pokedex.pokemon.domain.exception.RepositoryUnavailableException;
import com.mdas.api.g6.pokedex.pokemon.domain.valueobject.PokemonId;
import com.mdas.api.g6.pokedex.pokemon.infrastructure.pokeapi.entity.PokemonApiEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class PokeHttpRepository {

    private RestTemplate restTemplate = new RestTemplate();
    @Value("${pokeapi.url}")
    private String baseUrl;

    public PokemonApiEntity getPokemonById(PokemonId pokemonId) throws PokemonNotFoundException, RepositoryUnavailableException {
        String url = baseUrl + "/pokemon/" + pokemonId.getId();
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

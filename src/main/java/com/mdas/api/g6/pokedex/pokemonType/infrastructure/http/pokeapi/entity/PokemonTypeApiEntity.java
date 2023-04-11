package com.mdas.api.g6.pokedex.pokemonType.infrastructure.http.pokeapi.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mdas.api.g6.pokedex.pokemonType.infrastructure.http.pokeapi.entity.deserializer.PokemonTypeApiEntityDeserializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonDeserialize(using = PokemonTypeApiEntityDeserializer.class)
public class PokemonTypeApiEntity {
    @JsonProperty("name")
    private String name;

    @JsonProperty("url")
    private String url;
}


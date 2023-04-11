package com.mdas.api.g6.pokedex.pokemonType.infrastructure.http.pokeapi.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PokemonApiEntity {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("types")
    private List<PokemonTypeApiEntity> types;
}

package com.mdas.api.g6.pokedex.pokemon.domain;

import com.mdas.api.g6.pokedex.pokemon.domain.valueobject.PokemonHeight;
import com.mdas.api.g6.pokedex.pokemon.domain.valueobject.PokemonId;
import com.mdas.api.g6.pokedex.pokemon.domain.valueobject.PokemonName;
import com.mdas.api.g6.pokedex.pokemon.domain.valueobject.PokemonWeight;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Pokemon {
    private PokemonId id;
    private PokemonName name;
    private PokemonHeight height;
    private PokemonWeight weight;
}

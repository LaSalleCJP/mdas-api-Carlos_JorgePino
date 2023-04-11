package com.mdas.api.g6.pokedex.pokemonType.domain;

import com.mdas.api.g6.pokedex.pokemonType.domain.valueobject.PokemonId;
import com.mdas.api.g6.pokedex.pokemonType.domain.valueobject.PokemonName;
import com.mdas.api.g6.pokedex.pokemonType.domain.valueobject.PokemonTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Pokemon {
    private PokemonId id;
    private PokemonName name;
    private PokemonTypes types;
}

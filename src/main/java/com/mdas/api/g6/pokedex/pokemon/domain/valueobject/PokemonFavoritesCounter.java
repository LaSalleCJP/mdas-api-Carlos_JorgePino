package com.mdas.api.g6.pokedex.pokemon.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PokemonFavoritesCounter {

    public Integer value;

    public void incrementCount() {
        if (value == null) {
            value = 0;
        }
        value++;

        System.out.println(value);
    }

}

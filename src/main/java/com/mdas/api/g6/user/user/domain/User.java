package com.mdas.api.g6.user.user.domain;

import com.mdas.api.g6.user.user.domain.exception.PokemonAlreadyAddException;
import com.mdas.api.g6.user.user.domain.valueobject.FavoritePokemon;
import com.mdas.api.g6.user.user.domain.valueobject.FavoritePokemons;
import com.mdas.api.g6.user.user.domain.valueobject.UserId;
import com.mdas.api.g6.user.user.domain.valueobject.UserName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class User {
    private UserId id;
    private UserName name;
    private FavoritePokemons favoritePokemons;

    public void addFavoritePokemon(FavoritePokemon favoritePokemon) throws PokemonAlreadyAddException {
        favoritePokemons.add(favoritePokemon);
    }
}

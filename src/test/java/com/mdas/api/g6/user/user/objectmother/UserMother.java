package com.mdas.api.g6.user.user.objectmother;

import com.mdas.api.g6.user.user.domain.User;
import com.mdas.api.g6.user.user.domain.valueobject.FavoritePokemons;
import com.mdas.api.g6.user.user.domain.valueobject.UserId;
import com.mdas.api.g6.user.user.domain.valueobject.UserName;

public class UserMother {

    public static User random() {
        return new User(UserIdMother.random(), UserNameMother.random(), FavoritePokemonsMother.random());
    }

    public static User random(UserId userId) {
        return new User(userId, UserNameMother.random(), FavoritePokemonsMother.random());
    }

    public static User random(UserName userName) {
        return new User(UserIdMother.random(), userName, FavoritePokemonsMother.random());
    }

    public static User random(FavoritePokemons favoritePokemons) {
        return new User(UserIdMother.random(), UserNameMother.random(), favoritePokemons);
    }

    public static User random(UserId userId, UserName userName) {
        return new User(userId, userName, FavoritePokemonsMother.random());
    }

    public static User random(UserId userId, UserName userName, FavoritePokemons favoritePokemons) {
        return new User(userId, userName, favoritePokemons);
    }
}

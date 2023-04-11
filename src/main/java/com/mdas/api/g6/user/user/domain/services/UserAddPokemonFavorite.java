package com.mdas.api.g6.user.user.domain.services;

import com.mdas.api.g6.user.user.domain.User;
import com.mdas.api.g6.user.user.domain.exception.PokemonAlreadyAddException;
import com.mdas.api.g6.user.user.domain.repository.UserRepository;
import com.mdas.api.g6.user.user.domain.valueobject.FavoritePokemon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAddPokemonFavorite {

    private final UserRepository userRepository;

    public User addPokemonFavorite(User user, FavoritePokemon favoritePokemon) throws PokemonAlreadyAddException {
        user.addFavoritePokemon(favoritePokemon);
        userRepository.update(user);
        return user;
    }
}

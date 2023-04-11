package com.mdas.api.g6.user.user.application;

import com.mdas.api.g6.user.user.domain.User;
import com.mdas.api.g6.user.user.domain.exception.PokemonAlreadyAddException;
import com.mdas.api.g6.user.user.domain.exception.UserNotFoundException;
import com.mdas.api.g6.user.user.domain.services.UserAddPokemonFavorite;
import com.mdas.api.g6.user.user.domain.services.UserFinder;
import com.mdas.api.g6.user.user.domain.valueobject.FavoritePokemon;
import com.mdas.api.g6.user.user.domain.valueobject.UserId;
import com.mdas.api.g6.user.user.infrastructure.controller.dto.AddFavoritePokemonRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AddPokemonFavoriteUseCase {

    private final UserAddPokemonFavorite userAddPokemonFavorite;
    private final UserFinder userFinder;

    public User execute(Long userId, AddFavoritePokemonRequest request)
            throws PokemonAlreadyAddException, UserNotFoundException, IllegalArgumentException {
        User user = userFinder.getUserById(new UserId(userId));
        return userAddPokemonFavorite.addPokemonFavorite(user, new FavoritePokemon(request.getPokemonId()));
    }
}

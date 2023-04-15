package com.mdas.api.g6.user.user.domain.services;

import com.mdas.api.g6.user.user.domain.User;
import com.mdas.api.g6.user.user.domain.exception.PokemonAlreadyAddException;
import com.mdas.api.g6.user.user.domain.repository.UserRepository;
import com.mdas.api.g6.user.user.domain.valueobject.FavoritePokemon;
import com.mdas.api.g6.user.user.infrastructure.publisher.Publisher;
import com.mdas.api.g6.user.user.infrastructure.publisher.dto.MessagePublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAddPokemonFavorite {

    private final UserRepository userRepository;

    private final Publisher publisher;

    public User addPokemonFavorite(User user, FavoritePokemon favoritePokemon) throws PokemonAlreadyAddException {
        user.addFavoritePokemon(favoritePokemon);
        userRepository.update(user);

        publisher.send(new MessagePublisher(favoritePokemon.getId()));

        return user;
    }
}

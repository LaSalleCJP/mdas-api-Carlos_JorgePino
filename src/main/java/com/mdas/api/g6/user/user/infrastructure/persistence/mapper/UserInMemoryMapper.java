package com.mdas.api.g6.user.user.infrastructure.persistence.mapper;

import com.mdas.api.g6.user.user.domain.User;
import com.mdas.api.g6.user.user.domain.valueobject.FavoritePokemon;
import com.mdas.api.g6.user.user.domain.valueobject.FavoritePokemons;
import com.mdas.api.g6.user.user.domain.valueobject.UserId;
import com.mdas.api.g6.user.user.domain.valueobject.UserName;
import com.mdas.api.g6.user.user.infrastructure.persistence.entity.UserInMemoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserInMemoryMapper {
    @Mapping(target = "id", qualifiedByName = "mapIdToDomain")
    @Mapping(target = "name", qualifiedByName = "mapNameToDomain")
    @Mapping(target = "favoritePokemons", qualifiedByName = "mapFavoritePokemonsToDomain")
    User toDomain(UserInMemoryEntity userInMemoryEntity);
    @Mapping(target = "id", qualifiedByName = "mapIdToEntity")
    @Mapping(target = "name", qualifiedByName = "mapNameToEntity")
    @Mapping(target = "favoritePokemons", qualifiedByName = "mapFavoritePokemonsToEntity")
    UserInMemoryEntity toEntity(User user);

    @Named("mapIdToDomain")
    default UserId mapIdToDomain(Long id) {
        return new UserId(id);
    }

    @Named("mapNameToDomain")
    default UserName mapNameToDomain(String name) {
        return new UserName(name);
    }

    @Named("mapFavoritePokemonsToDomain")
    default FavoritePokemons mapFavouritePokemonsToDomain(Set<Integer> favouritePokemons) {
        Set<FavoritePokemon> favoritePokemonsSet = favouritePokemons.stream()
                .map(FavoritePokemon::new)
                .collect(Collectors.toSet());
        return new FavoritePokemons(favoritePokemonsSet);
    }

    @Named("mapIdToEntity")
    default Long mapIdToEntity(UserId userId) {
        return userId.getId();
    }

    @Named("mapNameToEntity")
    default String mapNameToEntity(UserName userName) {
        return userName.getName();
    }

    @Named("mapFavoritePokemonsToEntity")
    default Set<Integer> mapFavouritePokemonsToEntity(FavoritePokemons favoritePokemons) {
        return favoritePokemons.getFavoritePokemons().stream()
                .map(FavoritePokemon::getId)
                .collect(Collectors.toSet());
    }
}



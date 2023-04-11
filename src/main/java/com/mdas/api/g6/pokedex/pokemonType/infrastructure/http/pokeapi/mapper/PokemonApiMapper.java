package com.mdas.api.g6.pokedex.pokemonType.infrastructure.http.pokeapi.mapper;

import com.mdas.api.g6.pokedex.pokemonType.domain.Pokemon;
import com.mdas.api.g6.pokedex.pokemonType.domain.valueobject.PokemonId;
import com.mdas.api.g6.pokedex.pokemonType.domain.valueobject.PokemonName;
import com.mdas.api.g6.pokedex.pokemonType.domain.valueobject.PokemonTypes;
import com.mdas.api.g6.pokedex.pokemonType.infrastructure.http.pokeapi.entity.PokemonApiEntity;
import com.mdas.api.g6.pokedex.pokemonType.infrastructure.http.pokeapi.entity.PokemonTypeApiEntity;
import com.mdas.api.g6.pokedex.pokemonType.domain.valueobject.PokemonType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PokemonApiMapper {
    @Mapping(target = "id", qualifiedByName = "mapId")
    @Mapping(target = "name", qualifiedByName = "mapName")
    @Mapping(target = "types", qualifiedByName = "mapTypes")
    Pokemon toDomain(PokemonApiEntity pokemonApiEntity);

    @Named("mapId")
    default PokemonId mapName(Integer id) {
        return new PokemonId(id);
    }

    @Named("mapName")
    default PokemonName mapName(String name) {
        return new PokemonName(name);
    }

    @Named("mapTypes")
    default PokemonTypes mapTypes(List<PokemonTypeApiEntity> pokemonTypeApiEntities) {
        List<PokemonType> types = pokemonTypeApiEntities.stream()
                .map(pokemonTypeApiEntity -> new PokemonType(pokemonTypeApiEntity.getName()))
                .collect(Collectors.toList());
        return new PokemonTypes(types);
    }
}



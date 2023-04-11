package com.mdas.api.g6.pokedex.pokemon.infrastructure.pokeapi.mapper;

import com.mdas.api.g6.pokedex.pokemon.domain.Pokemon;
import com.mdas.api.g6.pokedex.pokemon.domain.valueobject.PokemonHeight;
import com.mdas.api.g6.pokedex.pokemon.domain.valueobject.PokemonId;
import com.mdas.api.g6.pokedex.pokemon.domain.valueobject.PokemonName;
import com.mdas.api.g6.pokedex.pokemon.domain.valueobject.PokemonWeight;
import com.mdas.api.g6.pokedex.pokemon.infrastructure.pokeapi.entity.PokemonApiEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PokemonMapper {
    @Mapping(target = "id", qualifiedByName = "mapPkId")
    @Mapping(target = "name", qualifiedByName = "mapPkName")
    @Mapping(target = "height", qualifiedByName = "mapPkHeight")
    @Mapping(target = "weight", qualifiedByName = "mapPkWeight")
    Pokemon toDomain(PokemonApiEntity pokemonApiEntity);

    @Named("mapPkId")
    default PokemonId mapId(Integer id) {
        return new PokemonId(id);
    }

    @Named("mapPkName")
    default PokemonName mapName(String name) {
        return new PokemonName(name);
    }

    @Named("mapPkHeight")
    default PokemonHeight mapHeight(double height) {
        return new PokemonHeight(height);
    }

    @Named("mapPkWeight")
    default PokemonWeight mapWeight(double weight) {
        return new PokemonWeight(weight);
    }
}



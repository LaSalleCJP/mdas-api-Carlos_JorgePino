package com.mdas.api.g6.pokedex.pokemonType.infrastructure.http.pokeapi.entity.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.mdas.api.g6.pokedex.pokemonType.infrastructure.http.pokeapi.entity.PokemonTypeApiEntity;

import java.io.IOException;

public class PokemonTypeApiEntityDeserializer extends JsonDeserializer<PokemonTypeApiEntity> {
    @Override
    public PokemonTypeApiEntity deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);
        String name = node.get("type").get("name").asText();
        String url = node.get("type").get("url").asText();
        return new PokemonTypeApiEntity(name, url);
    }
}

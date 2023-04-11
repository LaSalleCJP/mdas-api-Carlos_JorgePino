package com.mdas.api.g6.user.user.infrastructure.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddFavoritePokemonRequest {

    @JsonProperty("pokemonId")
    private Integer pokemonId;
}

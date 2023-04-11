package com.mdas.api.g6.user.user.infrastructure.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserInMemoryEntity {
    private Long id;

    private String name;

    private Set<Integer> favoritePokemons;
}

package com.mdas.api.g6.pokedex.pokemonType.infrastructure.console.controller;

import com.mdas.api.g6.pokedex.pokemonType.domain.exception.RepositoryUnavailableException;
import com.mdas.api.g6.pokedex.pokemonType.application.GetPokemonByNameUseCase;
import com.mdas.api.g6.pokedex.pokemonType.domain.exception.PokemonNotFoundException;
import com.mdas.api.g6.pokedex.pokemonType.domain.valueobject.PokemonType;
import com.mdas.api.g6.pokedex.pokemonType.domain.valueobject.PokemonTypes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class PokemonConsoleController {

    private final GetPokemonByNameUseCase getPokemonByNameUseCase;

    /**
     * Handles the different user commands.
     */
    public void handleCommand(List<String> input) {
        if (Objects.requireNonNull(getCommand(input)) == Command.TYPE) {
            handleTypeCommand(input);
        } else {
            handleInvalidCommand();
        }
    }

    /**
     * Parses the user input and returns the corresponding command.
     */
    private Command getCommand(List<String> input) {
        if (input.size() != 2) {
            return Command.INVALID;
        }
        if (input.get(0).equalsIgnoreCase("type")) {
            return Command.TYPE;
        }
        return Command.INVALID;
    }

    /**
     * Handles the "type" command.
     */
    private void handleTypeCommand(List<String> input) {
        String pokemonName = input.get(1);
        PokemonTypes pokemonTypes;
        try {
            pokemonTypes = getPokemonByNameUseCase.execute(pokemonName).getTypes();
        } catch (PokemonNotFoundException | RepositoryUnavailableException e) {
            System.out.println(
                    "Error occurred trying to fetch pokemon types.\n" +
                    "Error: " + e.getMessage()
            );
            return;
        } catch (Exception e) {
            System.out.println("Unexpected error occurred trying to fetch pokemon types.");
            return;
        }
        String commaSeparatedTypes = pokemonTypes.getTypes()
                .stream()
                .map(PokemonType::getName)
                .collect(Collectors.joining(", "));
        System.out.println(commaSeparatedTypes);
    }

    /**
     * Handles invalid user commands.
     */
    private void handleInvalidCommand() {
        System.out.println(
                "Invalid command. Pokemon command must be in the following form: \n" +
                "'pokemon type <pokemon_name>'"
        );
    }

    /**
     * Represents the different user commands.
     */
    private enum Command {
        TYPE, INVALID
    }

}

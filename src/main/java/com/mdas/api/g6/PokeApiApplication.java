package com.mdas.api.g6;


import com.mdas.api.g6.pokedex.pokemonType.infrastructure.console.controller.PokemonConsoleController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class PokeApiApplication {
	private final PokemonConsoleController pokemonConsoleController;

	public PokeApiApplication(PokemonConsoleController pokemonConsoleController) {
		this.pokemonConsoleController = pokemonConsoleController;
	}

	public static void main(String[] args) {
		SpringApplication.run(PokeApiApplication.class, args);
	}

	@Bean
	@Profile("!test")
	public CommandLineRunner init() {
		return args -> {
			try (Scanner scanner = new Scanner(System.in)) {
				String input;
				do {
					System.out.print("Enter a command (or 'q' to quit): ");
					input = scanner.nextLine();
					handleCommand(Arrays.asList(input.split(" ")));
				} while (!input.equalsIgnoreCase("q"));
			}
		};
	}

	/**
	 * Handles the different commands available on the application.
	 */
	private void handleCommand(List<String> input) {
		switch (getCommand(input)) {
			case POKEMON:
				pokemonConsoleController.handleCommand(input.subList(1, input.size()));
				break;
			case QUIT:
				System.out.println("Closing console application...");
				break;
			default:
				handleInvalidCommand();
		}
	}

	/**
	 * Parses the user input and returns the corresponding command.
	 */
	private PokeApiApplication.Command getCommand(List<String> input) {
		if (input.get(0).equalsIgnoreCase("q")) {
			return Command.QUIT;
		}
		if (input.get(0).equalsIgnoreCase("pokemon")) {
			return Command.POKEMON;
		}
		return Command.INVALID;
	}

	/**
	 * Handles invalid user commands.
	 */
	private void handleInvalidCommand() {
		System.out.println(
			"Invalid command. Commands:\n" +
			"* 'pokemon type <pokemon_name>'   --> Obtains the desired pokemon types.\n" +
			"* 'q'                             --> Exit application."
		);
	}

	/**
	 * Represents the different user commands.
	 */
	private enum Command {
		POKEMON, QUIT, INVALID
	}
}

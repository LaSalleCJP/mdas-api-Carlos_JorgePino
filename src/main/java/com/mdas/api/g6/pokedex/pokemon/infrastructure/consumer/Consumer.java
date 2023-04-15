package com.mdas.api.g6.pokedex.pokemon.infrastructure.consumer;

import com.mdas.api.g6.pokedex.pokemon.application.AddPokemonAsFavoriteUseCase;
import com.mdas.api.g6.pokedex.pokemon.domain.exception.PokemonNotFoundException;
import com.mdas.api.g6.pokedex.pokemon.domain.exception.RepositoryUnavailableException;
import com.mdas.api.g6.pokedex.pokemon.infrastructure.consumer.dto.MessageConsumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class Consumer {

    public final AddPokemonAsFavoriteUseCase service;

    @RabbitListener(queues = { "${sacavix.queue.name}" })
    public void receive(@Payload MessageConsumer message) throws PokemonNotFoundException, RepositoryUnavailableException {

        System.out.println("Received message: " + message);
        service.Execute(message.getPokemonId());
        makeSlow();
    }

    private void makeSlow() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

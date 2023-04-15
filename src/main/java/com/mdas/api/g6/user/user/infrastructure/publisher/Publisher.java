package com.mdas.api.g6.user.user.infrastructure.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
@RequiredArgsConstructor
public class Publisher {

    public final RabbitTemplate rabbitTemplate;

    public final Queue queue;

    public void send(Object message) {
        rabbitTemplate.convertAndSend(queue.getName(), message);
    }
}

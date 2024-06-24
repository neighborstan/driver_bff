package tech.saas.driver.user.watcher.processor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import tech.saas.driver.common.core.user.UserDomain;
import tech.saas.driver.user.core.uc.CreateUserUseCase;

import java.util.Map;


@Slf4j
@Component
@RequiredArgsConstructor
public class EventsProcessor {

    private final CreateUserUseCase createUserUseCase;

    @RabbitListener(queues = "${services.rabbitmq.queue}")
    public void handleEvent(@Payload UserDomain userDomain, @Headers Map<String, Object> headers) {

        log.info("Получено сообщение из очереди: {}", userDomain);
        log.info("Заголовки сообщения: {}", headers);
        createUserUseCase.create(userDomain);
    }
}

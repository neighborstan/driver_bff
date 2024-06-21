package tech.saas.driver.adaptertms.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import tech.saas.driver.adaptertms.entity.tms.payload.UserData;
import tech.saas.driver.adaptertms.mapper.UserMapper;
import tech.saas.driver.adaptertms.service.TmsPayloadHandler;
import tech.saas.driver.common.core.domain.UserDomain;

/**
 * Обработчик и отправитель данных по юзеру в очередь
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserDataTmsPayloadHandler implements TmsPayloadHandler<UserData> {

    private final UserMapper userMapper;
    private final RabbitTemplate rabbitTemplate;

    @Value("${services.rabbitmq.exchange}")
    private String userExchange;

    @Value("${services.rabbitmq.routing-key.user}")
    private String userRoutingKey;


    @Override
    public void handle(UserData payload) {
        UserDomain userDomain = userMapper.toDomain(payload);
        log.info("Отправляем сообщение в очередь: {}", userDomain);
        UserDomain userDomainResponse = rabbitTemplate.convertSendAndReceiveAsType(userExchange,
            userRoutingKey,
            userDomain,
            new ParameterizedTypeReference<UserDomain>() {
            });
        log.info("Отправлено сообщение: {}", userDomainResponse);
    }

    @Override
    public Class<UserData> getTmsPayloadType() {
        return UserData.class;
    }
}


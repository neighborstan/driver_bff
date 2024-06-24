package tech.saas.driver.adaptertms.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import tech.saas.driver.adaptertms.entity.tms.payload.TmsPayload;
import tech.saas.driver.adaptertms.entity.tms.payload.UserData;
import tech.saas.driver.adaptertms.mapper.UserMapper;
import tech.saas.driver.common.core.user.UserDomain;
import tech.saas.driver.adaptertms.service.TmsPayloadHandler;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdapterTmsServiceImplTest {

    @Mock
    private UserMapper userMapper;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private UserDataTmsPayloadHandler userDataHandler;

    @Captor
    private ArgumentCaptor<UserDomain> userDomainCaptor;

    private AdapterTmsServiceImpl adapterTmsService;

    @Value("${services.rabbitmq.exchange}")
    private String userExchange;

    @Value("${services.rabbitmq.routing-key.user}")
    private String userRoutingKey;

    @BeforeEach
    void setUp() {
        userDataHandler = new UserDataTmsPayloadHandler(userMapper, rabbitTemplate);

        Map<Class<? extends TmsPayload>, TmsPayloadHandler<? extends TmsPayload>> payloadHandlers = new HashMap<>();
        payloadHandlers.put(UserData.class, userDataHandler);
        adapterTmsService = new AdapterTmsServiceImpl(payloadHandlers);
    }

    @Test
    void should_send_tms_payload_to_queue_successfully() {
        // Arrange
        UserData userData = new UserData();
        UserDomain userDomain = new UserDomain();
        userDomain.setUserUID("testUID");
        userDomain.setUserFullName("user full name");
        userDomain.setRole("role");
        userDomain.setPhoneNumber("1234567890");
        userDomain.setAccess(true);
        userDomain.setDeleted(false);
        when(userMapper.toDomain(userData)).thenReturn(userDomain);

        // Act
        adapterTmsService.sendTmsPayloadToQueue(userData);

        // Assert
        verify(rabbitTemplate).convertSendAndReceiveAsType(eq(userExchange), eq(userRoutingKey), userDomainCaptor.capture(), any(ParameterizedTypeReference.class));
        UserDomain sentUserDomain = userDomainCaptor.getValue();
        assertEquals(userDomain, sentUserDomain);
    }
}

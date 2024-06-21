package tech.saas.driver.user.watcher.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import tech.saas.driver.common.SystemSource;
import tech.saas.driver.common.core.domain.UserDomain;
import tech.saas.driver.user.core.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EventsProcessorTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private EventsProcessor eventsProcessor;

    @Captor
    private ArgumentCaptor<UserDomain> userDomainCaptor;

    @Test
    void testHandleEvent() {
        // Arrange
        UserDomain userDomain = new UserDomain();
        userDomain.setUserUID("testUID");
        userDomain.setUserFullName("Test User");
        userDomain.setRole("testRole");
        userDomain.setPhoneNumber("1234567890");
        userDomain.setAccess(true);
        userDomain.setDeleted(false);
        userDomain.setSystemSource(SystemSource.TMS);

        ObjectMapper objectMapper = new ObjectMapper();
        MessageConverter messageConverter = new Jackson2JsonMessageConverter(objectMapper);

        // Имитируем сериализацию после отправки в очередь
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("customHeader1", "headerValue1");
        messageProperties.setHeader("customHeader2", "headerValue2");
        Message message = messageConverter.toMessage(userDomain, messageProperties);

        // Имитируем десериализацию в лиснере
        UserDomain deserializedUserDomain = (UserDomain) messageConverter.fromMessage(message);

        // Act
        eventsProcessor.handleEvent(deserializedUserDomain, message.getMessageProperties().getHeaders());

        // Assert
        verify(userService).saveUser(userDomainCaptor.capture());

        UserDomain capturedUserDomain = userDomainCaptor.getValue();

        assertEquals(userDomain.getUserUID(), capturedUserDomain.getUserUID());
        assertEquals(userDomain.getUserFullName(), capturedUserDomain.getUserFullName());
        assertEquals(userDomain.getRole(), capturedUserDomain.getRole());
        assertEquals(userDomain.getPhoneNumber(), capturedUserDomain.getPhoneNumber());
        assertEquals(userDomain.isAccess(), capturedUserDomain.isAccess());
        assertEquals(userDomain.isDeleted(), capturedUserDomain.isDeleted());
        assertEquals(userDomain.getSystemSource(), capturedUserDomain.getSystemSource());

        // Проверка заголовков
        assertEquals("headerValue1", message.getMessageProperties().getHeaders().get("customHeader1"));
        assertEquals("headerValue2", message.getMessageProperties().getHeaders().get("customHeader2"));
    }
}


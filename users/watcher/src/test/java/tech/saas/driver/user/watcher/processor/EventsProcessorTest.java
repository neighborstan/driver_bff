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
import tech.saas.driver.common.core.user.UserDomain;
import tech.saas.driver.user.core.uc.CreateUserUseCase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EventsProcessorTest {

    @Mock
    private CreateUserUseCase createUserUC;

    @InjectMocks
    private EventsProcessor eventsProcessor;

    @Captor
    private ArgumentCaptor<UserDomain> userDomainCaptor;


    @Test
    void testHandleEvent() {
        // Arrange
        UserDomain userDomain = createUserDomain();
        Message message = serializeUserDomain(userDomain); //  имитируем сериализацию при помощи Jackson2JsonMessageConverter
        UserDomain deserializedUserDomain = deserializeUserDomain(message); // имитируем десериализацию при помощи Jackson2JsonMessageConverter

        // Act
        eventsProcessor.handleEvent(deserializedUserDomain, message.getMessageProperties().getHeaders());

        // Assert
        verify(createUserUC).create(userDomainCaptor.capture());
        UserDomain capturedUserDomain = userDomainCaptor.getValue();
        assertUserDomainsEqual(userDomain, capturedUserDomain);
        assertEquals("headerValue1", message.getMessageProperties().getHeaders().get("customHeader1"));
        assertEquals("headerValue2", message.getMessageProperties().getHeaders().get("customHeader2"));
    }


    private UserDomain createUserDomain() {
        UserDomain userDomain = new UserDomain();
        userDomain.setUserUID("testUID");
        userDomain.setUserFullName("Test User");
        userDomain.setRole("testRole");
        userDomain.setPhoneNumber("1234567890");
        userDomain.setAccess(true);
        userDomain.setDeleted(false);
        userDomain.setSystemSource(SystemSource.TMS);
        return userDomain;
    }

    private Message serializeUserDomain(UserDomain userDomain) {
        ObjectMapper objectMapper = new ObjectMapper();
        MessageConverter messageConverter = new Jackson2JsonMessageConverter(objectMapper);
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("customHeader1", "headerValue1");
        messageProperties.setHeader("customHeader2", "headerValue2");
        return messageConverter.toMessage(userDomain, messageProperties);
    }

    private UserDomain deserializeUserDomain(Message message) {
        MessageConverter messageConverter = new Jackson2JsonMessageConverter(new ObjectMapper());
        return (UserDomain) messageConverter.fromMessage(message);
    }

    private void assertUserDomainsEqual(UserDomain expected, UserDomain actual) {
        assertEquals(expected.getUserUID(), actual.getUserUID());
        assertEquals(expected.getUserFullName(), actual.getUserFullName());
        assertEquals(expected.getRole(), actual.getRole());
        assertEquals(expected.getPhoneNumber(), actual.getPhoneNumber());
        assertEquals(expected.isAccess(), actual.isAccess());
        assertEquals(expected.isDeleted(), actual.isDeleted());
        assertEquals(expected.getSystemSource(), actual.getSystemSource());
    }
}


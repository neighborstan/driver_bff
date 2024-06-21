package tech.saas.driver.user.watcher.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ComponentScan({
        "tech.saas.driver.user.watcher",
        "tech.saas.driver.user.core",
        "tech.saas.driver.common"
})
@RequiredArgsConstructor
public class WatcherConfig {

    private final RabbitMQProperties rabbitMQProperties;


    @Bean
    public Queue userQueue() {
        return createQueue(rabbitMQProperties.getQueue(), rabbitMQProperties.getDlqQueue(), rabbitMQProperties.getDlqRoutingKey());
    }

    @Bean
    public Queue deadLetterQueue() {
        return createQueue(rabbitMQProperties.getDlqQueue(), null, null);
    }

    @Bean
    public TopicExchange userExchange() {
        return createExchange(rabbitMQProperties.getExchange());
    }

    @Bean
    public List<Binding> userBindings() {
        return rabbitMQProperties.getRoutingKeys().stream()
            .map(routingKey -> createBinding(userQueue(), userExchange(), routingKey))
            .toList();
    }

    @Bean
    public Binding dlqBinding() {
        return createBinding(deadLetterQueue(), userExchange(), rabbitMQProperties.getDlqRoutingKey());
    }

    @Bean
    public MessageConverter jsonMessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter jsonMessageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter);
        return rabbitTemplate;
    }


    private Queue createQueue(String queueName, String dlqName, String dlqRoutingKey) {
        QueueBuilder builder = QueueBuilder.durable(queueName)
            .withArgument("x-queue-type", "classic");

        if (dlqName != null && dlqRoutingKey != null) {
            builder.withArgument("x-dead-letter-exchange", rabbitMQProperties.getExchange())
                .withArgument("x-dead-letter-routing-key", dlqRoutingKey);
        }

        return builder.build();
    }

    private TopicExchange createExchange(String exchangeName) {
        return ExchangeBuilder.topicExchange(exchangeName)
            .durable(true)
            .build();
    }

    private Binding createBinding(Queue queue, TopicExchange exchange, String routingKey) {
        return BindingBuilder.bind(queue)
            .to(exchange)
            .with(routingKey);
    }
}

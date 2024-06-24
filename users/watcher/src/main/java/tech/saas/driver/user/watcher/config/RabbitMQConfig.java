package tech.saas.driver.user.watcher.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig {

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
    public TopicExchange exchange() {
        return createExchange(rabbitMQProperties.getExchange());
    }

    @Bean
    public List<Binding> userBindings() {
        return rabbitMQProperties.getRoutingKeys().stream()
            .map(routingKey -> createBinding(userQueue(), exchange(), routingKey))
            .toList();
    }

    @Bean
    public Binding dlqBinding() {
        return createBinding(deadLetterQueue(), exchange(), rabbitMQProperties.getDlqRoutingKey());
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

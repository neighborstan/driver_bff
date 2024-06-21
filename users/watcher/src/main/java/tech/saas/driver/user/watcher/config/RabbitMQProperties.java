package tech.saas.driver.user.watcher.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "services.rabbitmq")
public class RabbitMQProperties {

    private String exchange;
    private String queue;
    private List<String> routingKeys;
    private String dlqQueue;
    private String dlqRoutingKey;
}

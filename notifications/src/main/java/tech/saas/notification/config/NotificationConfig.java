package tech.saas.notification.config;

import lombok.Data;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        "tech.saas.notification.config"
})
@Data
public class NotificationConfig {

}

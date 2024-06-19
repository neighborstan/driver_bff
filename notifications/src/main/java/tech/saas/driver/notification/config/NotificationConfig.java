package tech.saas.driver.notification.config;

import lombok.Data;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        "tech.saas.driver.notification",
        "tech.saas.driver.common"
})
@Data
public class NotificationConfig {

}

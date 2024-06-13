package saas.notification.config;

import config.CoreConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CoreConfig.class)
@ComponentScan({
        "saas.notification.config"
})
public class NotificationConfig {


}

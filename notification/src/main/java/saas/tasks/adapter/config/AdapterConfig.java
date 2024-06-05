package saas.tasks.notification.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import saas.tasks.core.config.CoreConfig;

@Configuration
@Import(CoreConfig.class)
@ComponentScan({
        "saas.tasks.notification.config"
})
public class NotificationConfig {


}

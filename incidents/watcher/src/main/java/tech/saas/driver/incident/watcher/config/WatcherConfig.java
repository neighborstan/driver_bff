package tech.saas.driver.incident.watcher.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        "tech.saas.driver.incident.watcher",
        "tech.saas.driver.common"
})
public class WatcherConfig {

}

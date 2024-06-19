package tech.saas.driver.chat.watcher.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        "tech.saas.driver.chat.watcher",
        "tech.saas.driver.common"
})
public class WatcherConfig {

}

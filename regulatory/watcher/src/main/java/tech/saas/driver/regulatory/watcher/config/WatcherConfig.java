package tech.saas.driver.regulatory.watcher.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        "tech.saas.driver.regulatory.watcher",
        "tech.saas.driver.common"
})
public class WatcherConfig {

}

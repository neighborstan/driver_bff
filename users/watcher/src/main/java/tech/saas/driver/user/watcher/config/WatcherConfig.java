package tech.saas.driver.user.watcher.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        "tech.saas.driver.user.watcher",
        "tech.saas.driver.user.core",
        "tech.saas.driver.common"
})
@RequiredArgsConstructor
public class WatcherConfig {

}

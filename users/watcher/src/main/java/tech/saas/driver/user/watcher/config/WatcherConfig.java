package tech.saas.driver.user.watcher.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import tech.saas.driver.common.core.config.CommonCoreConfig;

@Configuration
@Import(CommonCoreConfig.class)
@ComponentScan({
        "tech.saas.driver.user.watcher",
        "tech.saas.driver.common"
})
public class WatcherConfig {

}

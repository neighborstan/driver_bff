package tech.saas.driver.tasks.watcher.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import tech.saas.driver.common.core.config.CommonCoreConfig;
import tech.saas.driver.tasks.core.config.CoreConfig;

@Configuration
@Import({CommonCoreConfig.class, CoreConfig.class})
@ComponentScan({
        "tech.saas.driver.tasks.watcher",
        "tech.saas.driver.common"
})
public class WatcherConfig {

}

package tech.saas.user.watcher.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import tech.saas.common.core.config.CommonCoreConfig;

@Configuration
@Import(CommonCoreConfig.class)
@ComponentScan({
        "tech.saas.user.watcher.config"
})
public class WatcherConfig {

}

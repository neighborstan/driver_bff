package tech.saas.chat.watcher.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import tech.saas.chat.core.config.CoreConfig;

@Configuration
@Import(CoreConfig.class)
@ComponentScan({
        "tech.saas.chat.watcher.config"
})
public class WatcherConfig {

}

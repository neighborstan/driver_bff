package saas.tasks.adapter.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import saas.tasks.core.config.CoreConfig;

@Configuration
@Import(CoreConfig.class)
@ComponentScan({
        "saas.tasks.adapter.config"
})
public class AdapterConfig {


}

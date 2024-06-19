package tech.saas.driver.user.api.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import tech.saas.driver.common.core.config.CommonCoreConfig;

@Configuration
@Import(CommonCoreConfig.class)
@ComponentScan({
        "tech.saas.driver.user.api",
        "tech.saas.driver.user.core",
        "tech.saas.driver.common"
})
public class ApiConfig {
}

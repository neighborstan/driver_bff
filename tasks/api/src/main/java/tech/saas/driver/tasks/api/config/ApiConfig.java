package tech.saas.driver.tasks.api.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import tech.saas.driver.common.core.config.CommonCoreConfig;
import tech.saas.driver.tasks.core.config.CoreConfig;

@Configuration
@ComponentScan({
        "tech.saas.driver.tasks.api",
        "tech.saas.driver.tasks.core",
        "tech.saas.driver.common",
})
@Import({CommonCoreConfig.class, CoreConfig.class})
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class ApiConfig {


}

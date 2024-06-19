package tech.saas.driver.incident.api.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        "tech.saas.driver.incident.api",
        "tech.saas.driver.incident.core",
        "tech.saas.driver.common"
})
public class ApiConfig {

}

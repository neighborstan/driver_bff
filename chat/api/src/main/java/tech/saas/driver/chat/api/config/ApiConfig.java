package tech.saas.driver.chat.api.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        "tech.saas.driver.chat.api",
        "tech.saas.driver.chat.core",
        "tech.saas.driver.common",
})
public class ApiConfig {

}

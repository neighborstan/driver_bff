package tech.saas.driver.regulatory.api.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        "tech.saas.driver.regulatory.api",
        "tech.saas.driver.regulatory.core",
        "tech.saas.driver.common"
})
public class ApiConfig {

}

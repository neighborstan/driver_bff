package tech.saas.driver.adaptertms.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        "tech.saas.driver.adaptertms",
        "tech.saas.driver.common"
})
public class AdapterTmsConfig {

}

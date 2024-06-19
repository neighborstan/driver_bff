package tech.saas.driver.adaptertraffic.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        "tech.saas.driver.adaptertraffic",
        "tech.saas.driver.common"
})
public class AdapterTrafficConfig {

}

package tech.saas.driver.user.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@Configuration
@EnableJdbcRepositories(basePackages = {
    "tech.saas.driver.user.core.repository"
})
public class CoreConfig {
}

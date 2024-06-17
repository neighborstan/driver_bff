package tech.saas.regulatory.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class CoreConfig {

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }
}

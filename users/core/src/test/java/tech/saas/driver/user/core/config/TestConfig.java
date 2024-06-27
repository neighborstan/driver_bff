package tech.saas.driver.user.core.config;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@TestConfiguration
@Profile("test")
class TestConfig {

    @Bean
    @Primary
    public Keycloak keycloakAdmin(KeycloakProperties keycloakProperties) {
        return KeycloakBuilder.builder()
            .serverUrl(keycloakProperties.getUrl())
            .realm(keycloakProperties.getRealm())
            .clientId(keycloakProperties.getClientId())
            .username("admin")
            .password("admin")
            .build();
    }
}


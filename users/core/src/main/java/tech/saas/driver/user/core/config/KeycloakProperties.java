package tech.saas.driver.user.core.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("driver.keycloak")
@Setter
@Getter
@NoArgsConstructor
public class KeycloakProperties {
    private String url;
    private String realm;
    private String clientId;
    private String clientSecret;
    private String role;
}

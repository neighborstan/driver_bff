package tech.saas.driver.user.core;

import org.springframework.stereotype.Component;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;

/**
 * Bean для запуска контейнера Keycloak. При старте контейнера создается администратор с логином admin и паролем admin.
 */
@Component
public class KeycloakTestContainer extends GenericContainer<KeycloakTestContainer> implements AutoCloseable {

    private static final DockerImageName KEYCLOAK_IMAGE = DockerImageName.parse("quay.io/keycloak/keycloak:25.0.1");

    public KeycloakTestContainer() {
        super(KEYCLOAK_IMAGE);
        this.withExposedPorts(8080);
        this.withEnv("KEYCLOAK_ADMIN", "admin");
        this.withEnv("KEYCLOAK_ADMIN_PASSWORD", "admin");
        this.withCommand("start-dev");
        this.waitingFor(Wait.forHttp("/").forStatusCode(200).withStartupTimeout(Duration.ofMinutes(5)));
    }

    public String getAuthServerUrl() {
        return "http://" + this.getHost() + ":" + this.getMappedPort(8080);
    }

    @Override
    public void close() {
        this.stop();
    }
}


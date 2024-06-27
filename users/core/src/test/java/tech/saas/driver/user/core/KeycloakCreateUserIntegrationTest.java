package tech.saas.driver.user.core;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import tech.saas.driver.common.SystemSource;
import tech.saas.driver.common.core.user.UserDomain;
import tech.saas.driver.user.core.repository.UserJdbcRepository;
import tech.saas.driver.user.core.uc.CreateUserUseCase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Интеграционный тест для проверки создания пользователя в Keycloak.
 * <p>Профиль test используется для исключения DataSourceAutoConfiguration, так как тесты не требуют подключения к БД.</p>
 */
@ActiveProfiles("test")
@TestPropertySource(properties = {
    "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration"
})
class KeycloakCreateUserIntegrationTest extends BasicSpringTest {

    private static final String TEST_USER = "testuser";
    private static final String MASTER_REALM = "master";
    private static final String ADMIN_CLI = "admin-cli";
    private static final String ADMIN_AS_STRING = "admin";
    public static final String ROLE_USER = "user";

    @Autowired
    private CreateUserUseCase createUserUseCase;

    @Autowired
    private Keycloak keycloakAdmin;

    @MockBean
    private UserJdbcRepository userJdbcRepository;

    private static KeycloakTestContainer keycloakContainer; // для хранения контейнера Keycloak


    @BeforeAll
    static void setUp() {
        keycloakContainer = new KeycloakTestContainer();
        keycloakContainer.start();

        // Устанавливаем системные свойства (для подключения к контейнеру Keycloak)
        System.setProperty("driver.keycloak.url", keycloakContainer.getAuthServerUrl());
        System.setProperty("driver.keycloak.realm", MASTER_REALM);
        System.setProperty("driver.keycloak.clientId", ADMIN_CLI);
        System.setProperty("driver.keycloak.clientSecret", "");
        System.setProperty("driver.keycloak.role", ROLE_USER);

        // Создаем роль "user" в Keycloak заранее
        createRole(ROLE_USER);
    }

    @Test
    void testCreateUser() {
        // Arrange
        UserDomain user = getUserDomain();

        // Act
        createUserUseCase.create(user);

        // Assert
        // Проверка создания пользователя в Keycloak
        List<UserRepresentation> users;
        try (Keycloak keycloak = KeycloakBuilder.builder()
            .serverUrl(keycloakContainer.getAuthServerUrl())
            .realm(MASTER_REALM)
            .clientId(ADMIN_CLI)
            .username(ADMIN_AS_STRING)
            .password(ADMIN_AS_STRING)
            .build()) {

            users = keycloak.realm(MASTER_REALM).users().search(TEST_USER);
        }

        assertFalse(users.isEmpty());
        UserRepresentation createdUser = users.getFirst();
        assertEquals(TEST_USER, createdUser.getUsername());
    }


    private static @NotNull UserDomain getUserDomain() {
        UserDomain user = new UserDomain();
        user.setUserUID(TEST_USER);
        user.setUserFullName("Test User");
        user.setPhoneNumber("123456789");
        user.setAccess(true);
        user.setDeleted(false);
        user.setSystemSource(SystemSource.TMS);
        return user;
    }

    private static void createRole(String roleName) {
        try (Keycloak keycloakAdmin = KeycloakBuilder.builder()
            .serverUrl(keycloakContainer.getAuthServerUrl())
            .realm(MASTER_REALM)
            .clientId(ADMIN_CLI)
            .username(ADMIN_AS_STRING)
            .password(ADMIN_AS_STRING)
            .build()) {

            RealmResource realmResource = keycloakAdmin.realm(MASTER_REALM);
            RoleRepresentation roleRepresentation = new RoleRepresentation();
            roleRepresentation.setName(roleName);
            realmResource.roles().create(roleRepresentation);
        }
    }
}

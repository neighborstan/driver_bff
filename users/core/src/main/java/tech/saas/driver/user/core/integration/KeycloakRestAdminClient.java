package tech.saas.driver.user.core.integration;

import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Component;
import tech.saas.driver.common.core.user.UserDomain;
import tech.saas.driver.user.core.config.KeycloakProperties;
import tech.saas.driver.user.core.exception.KeycloakCreateUserException;
import tech.saas.driver.user.core.exception.KeycloakRoleNotFound;
import tech.saas.driver.user.core.exception.KeycloakRoleRepresentationException;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Класс для взаимодействия с Keycloak Admin API
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class KeycloakRestAdminClient implements KeycloakClient {

    private final Keycloak keycloakAdmin;
    private final KeycloakProperties keycloakProperties;


    /**
     * Создание пользователя в Keycloak
     *
     * @param userDomain домен пользователя из commons
     */
    @Override
    public void create(UserDomain userDomain) {
        UserRepresentation user = mapUser(userDomain);
        log.info("Создание пользователя в Keycloak с URL: {}, Realm: {}, ClientId: {}",
            keycloakProperties.getUrl(), keycloakProperties.getRealm(), keycloakProperties.getClientId());
        try (Response response = keycloakAdmin.realm(keycloakProperties.getRealm()).users().create(user)) {
            if (!isResponseSuccess(response)) {
                String responseBody = response.readEntity(String.class);
                log.error("Создать пользователя не удалось - статус: {}, тело ответа: {}", response.getStatus(), responseBody);
                throw new KeycloakCreateUserException("Ошибка создания пользователя " + userDomain.getUserUID());
            } else {
                log.info("Созданный пользователь {} - {}, {}", userDomain, response.getStatusInfo(), response.getHeaders());
                String userId = extractUserIdFromResponse(response);
                assignRoleToUser(userId, keycloakProperties.getRole());
            }
        } catch (Exception e) {
            log.error("Произошла ошибка при создании пользователя: ", e);
        }
    }


    private UserRepresentation mapUser(UserDomain userDomain) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userDomain.getUserUID());
        user.setLastName(userDomain.getUserFullName());
        user.setEnabled(userDomain.isAccess());
        user.setAttributes(Map.of(
            "userId", Collections.singletonList(userDomain.getUserUID()),
            "phoneNumber", Collections.singletonList(userDomain.getPhoneNumber())
        ));
        return user;
    }

    private void assignRoleToUser(String userId, String role) {
        try {
            log.info("Назначение роли {} пользователю {}", role, userId);

            // Вызов метода для вывода списка всех ролей
            List<RoleRepresentation> allRoles = getAllRoles();
            log.info("Доступные роли в realm: {}", allRoles.stream().map(RoleRepresentation::getName).collect(Collectors.toList()));

            RoleRepresentation roleRepresentation = getRoleRepresentation(role);
            keycloakAdmin.realm(keycloakProperties.getRealm())
                .users()
                .get(userId)
                .roles()
                .realmLevel()
                .add(Collections.singletonList(roleRepresentation));
            log.info("Назначена роль {} пользователю {}", role, userId);
        } catch (Exception e) {
            log.error("Не удалось назначить роль {} пользователю {}.: ", role, userId, e);
        }
    }

    private RoleRepresentation getRoleRepresentation(String role) {
        try {
            log.info("Получение представления для роли {}", role);
            RoleRepresentation roleRepresentation = keycloakAdmin.realm(keycloakProperties.getRealm())
                .roles()
                .get(role)
                .toRepresentation();
            if (roleRepresentation == null) {
                log.error("Роль {} не найдена в realm {}", role, keycloakProperties.getRealm());
                throw new KeycloakRoleNotFound("Роль " + role + " не найдена в realm " + keycloakProperties.getRealm());
            }
            return roleRepresentation;
        } catch (Exception e) {
            log.error("Не удалось получить представление для роли {}: ", role, e);
            throw new KeycloakRoleRepresentationException("Ошибка получения представления для " + role, e.toString());
        }
    }

    private String extractUserIdFromResponse(Response response) {
        String location = response.getLocation().getPath();
        return location.substring(location.lastIndexOf('/') + 1);
    }

    private static boolean isResponseSuccess(Response response) {
        return response.getStatusInfo() != null && response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL;
    }

    public List<RoleRepresentation> getAllRoles() {
        return keycloakAdmin.realm(keycloakProperties.getRealm()).roles().list();
    }

}

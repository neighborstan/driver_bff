package tech.saas.driver.user.core.integration;

import tech.saas.driver.common.core.user.UserDomain;

import java.util.Optional;

public interface KeycloakClient {

    Optional<String> create(UserDomain userDomain);

    boolean delete(String userId);
}

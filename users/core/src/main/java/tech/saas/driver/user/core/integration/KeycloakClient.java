package tech.saas.driver.user.core.integration;

import tech.saas.driver.common.core.user.UserDomain;

public interface KeycloakClient {

    void create(UserDomain userDomain);
}

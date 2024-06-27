package tech.saas.driver.user.core.uc.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.saas.driver.common.core.user.UserDomain;
import tech.saas.driver.user.core.integration.KeycloakClient;
import tech.saas.driver.user.core.repository.UserPersistenceAdapter;
import tech.saas.driver.user.core.uc.CreateUserUseCase;

@Service
@RequiredArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserPersistenceAdapter userStorage;
    private final KeycloakClient keycloakClient;

    @Override
    @Transactional
    public void create(UserDomain userDomain) {
        keycloakClient.create(userDomain);
        userStorage.save(userDomain);

    }
}

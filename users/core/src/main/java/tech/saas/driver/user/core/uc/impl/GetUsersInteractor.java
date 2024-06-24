package tech.saas.driver.user.core.uc.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.saas.driver.common.core.user.UserDomain;
import tech.saas.driver.user.core.integration.KeycloakClient;
import tech.saas.driver.user.core.repository.UserPersistanceAdapter;
import tech.saas.driver.user.core.uc.GetUsersUseCase;

@Service
@RequiredArgsConstructor
class GetUsersInteractor implements GetUsersUseCase {

    private final UserPersistanceAdapter userStorage;


    @Override
    public Iterable<UserDomain> listAll() {
        return userStorage.findAll();
    }
}

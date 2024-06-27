package tech.saas.driver.user.core.uc.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.saas.driver.common.core.user.UserDomain;
import tech.saas.driver.user.core.repository.UserPersistenceAdapter;
import tech.saas.driver.user.core.uc.GetUsersUseCase;

@Service
@RequiredArgsConstructor
class GetUsersUseCaseImpl implements GetUsersUseCase {

    private final UserPersistenceAdapter userStorage;


    @Override
    public Iterable<UserDomain> listAll() {
        return userStorage.findAll();
    }
}

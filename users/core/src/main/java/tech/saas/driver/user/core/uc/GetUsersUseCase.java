package tech.saas.driver.user.core.uc;

import tech.saas.driver.common.core.user.UserDomain;

public interface GetUsersUseCase {

    Iterable<UserDomain> listAll();
}

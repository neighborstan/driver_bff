package tech.saas.driver.user.core.repository;

import tech.saas.driver.common.core.user.UserDomain;
import tech.saas.driver.common.scaffold.persistence.IPersistenceAdapter;

public interface UserPersistenceAdapter extends IPersistenceAdapter<UserDomain> {

    UserDomain save(UserDomain userDomain);

    Iterable<UserDomain> findAll();
}

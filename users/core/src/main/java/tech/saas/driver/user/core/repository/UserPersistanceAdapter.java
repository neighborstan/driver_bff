package tech.saas.driver.user.core.repository;

import tech.saas.driver.common.core.user.UserDomain;
import tech.saas.driver.common.scaffold.persistance.IPersistanceAdapter;

public interface UserPersistanceAdapter extends IPersistanceAdapter<UserDomain> {


    UserDomain save(UserDomain userDomain);

    Iterable<UserDomain> findAll();
}
